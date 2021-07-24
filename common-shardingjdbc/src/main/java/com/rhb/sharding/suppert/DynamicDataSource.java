package com.rhb.sharding.suppert;

import com.alibaba.druid.pool.DruidDataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.encrypt.api.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptorRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sharding-jdbc的核心配置
 *
 * @author renhuibo
 * @date 2021/7/22 13:57
 */
@Slf4j
@Configuration
public class DynamicDataSource {

  private final String aeskey = "123456";

  private DruidDataSource createDruidDataSource(String url,String uname,String pwd){
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl(url);
    dataSource.setUsername(uname);
    dataSource.setPassword(pwd);
    return dataSource;
  }

  private Map<String,DataSource> buildDataSourceMap(){
    Map<String,DataSource> dataSourceMap = new HashMap<>(3);
    /**
     * 用一个库，模拟多个数据源
     */
    final String urlPrefix = "jdbc:mysql://124.71.80.133:3306/";
    final String uname = "root";
    final String psw = "yZcAtpyAplHElK9w";

    DruidDataSource dataSource0 = createDruidDataSource(urlPrefix+"db0",uname,psw);
    dataSourceMap.put("db0", dataSource0);

    DruidDataSource dataSource1 = createDruidDataSource(urlPrefix+"db1",uname,psw);
    dataSourceMap.put("db1", dataSource1);

    DruidDataSource dataSource2 = createDruidDataSource(urlPrefix+"db2",uname,psw);
    dataSourceMap.put("db2", dataSource2);

    return dataSourceMap;
  }

  private ShardingRuleConfiguration buildShardingRuleConfiguration(){
    /**
     * 配置表规则
     */
    TableRuleConfiguration orderTableRuleConfiguration = new TableRuleConfiguration("t_order","db${0..2}.t_order_${1..2}");
    // 配置分库策略（groovy表达式配置db规则）
    orderTableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id","db${user_id % 3}"));
    // 配置分表策略（groovy表达式配置表规则）
    orderTableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id","t_order_${order_id % 2 + 1}"));

    TableRuleConfiguration itemTableRuleConfiguration = new TableRuleConfiguration("t_item","db${0..2}.t_item_${1..2}");
    itemTableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id","db${user_id % 3}"));
    itemTableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("item_id","t_item_${item_id % 2 + 1}"));

    List<TableRuleConfiguration> tableRuleConfigurationList = new ArrayList<>();
    tableRuleConfigurationList.add(orderTableRuleConfiguration);
    tableRuleConfigurationList.add(itemTableRuleConfiguration);
    /**
     * 分片规则
     */
    ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
    shardingRuleConfiguration.getTableRuleConfigs().addAll(tableRuleConfigurationList);

    /**
     * 数据脱敏策略
     */
    shardingRuleConfiguration.setEncryptRuleConfig(buildEncryptRuleConfiguration());

    log.info("分片策略构建完成");
    return shardingRuleConfiguration;
  }

  public EncryptRuleConfiguration buildEncryptRuleConfiguration(){
    //自带aes算法需要
    Properties props = new Properties();
    props.setProperty("aes.key.value", aeskey);
    EncryptorRuleConfiguration encryptorConfig = new EncryptorRuleConfiguration("AES", props);

    //自定义算法
    //props.setProperty("qb.finance.aes.key.value", aeskey);
    //EncryptorRuleConfiguration encryptorConfig = new EncryptorRuleConfiguration("QB-FINANCE-AES", props);

    EncryptRuleConfiguration encryptRuleConfig = new EncryptRuleConfiguration();
    encryptRuleConfig.getEncryptors().put("aes", encryptorConfig);

    /**
     * START: card_info 表的脱敏配置
     */
    {
      EncryptColumnRuleConfiguration columnConfig1 = new EncryptColumnRuleConfiguration("pay_no", "pay_name", "", "aes");
      Map<String, EncryptColumnRuleConfiguration> columnConfigMaps = new HashMap<>();
      columnConfigMaps.put("pay_name", columnConfig1);
      EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(columnConfigMaps);
      encryptRuleConfig.getTables().put("t_item", tableConfig);
    }

    /**
     *  START: pay_order 表的脱敏配置
     {
     EncryptColumnRuleConfiguration columnConfig1 = new EncryptColumnRuleConfiguration("", "card_no", "", "aes");
     Map<String, EncryptColumnRuleConfiguration> columnConfigMaps = new HashMap<>();
     columnConfigMaps.put("card_no", columnConfig1);
     EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(columnConfigMaps);
     encryptRuleConfig.getTables().put("pay_order", tableConfig);
     }
     */
    log.info("脱敏配置构建完成");
    return encryptRuleConfig;
  }

  @Bean(name = "shardingDataSource")
  @Qualifier("shardingDataSource")
  public DataSource getShardingDataSource(){
    DataSource dataSource = null;
    try{
      /**
       * 打印sql日志信息: ConfigurationPropertyKey
       */
      Properties properties = new Properties();
      properties.setProperty("sql.show","true");
      properties.setProperty("sql.simple","true");
      /**
       * 1. 配置分片策略
       * 2. 配置数据脱敏策略
       */
      dataSource = ShardingDataSourceFactory.createDataSource(buildDataSourceMap(),buildShardingRuleConfiguration(),properties);
    }catch (Exception e){
      e.printStackTrace();
    }
    return dataSource;
  }

  @Bean(name = "druidDataSource")
  public DataSource druidDataSource(){
    DataSource dataSource = null;
    try{
      /**
       * 打印sql日志信息: ConfigurationPropertyKey
       */
      Properties properties = new Properties();
      properties.setProperty("sql.show","true");
      properties.setProperty("sql.simple","true");
      /**
       * 1. 配置分片策略
       * 2. 配置数据脱敏策略
       */
      dataSource = EncryptDataSourceFactory.createDataSource(getShardingDataSource(),buildEncryptRuleConfiguration(),new Properties());
    }catch (Exception e){
      e.printStackTrace();
    }
    return dataSource;
  }

  @Bean("keyGenerator")
  public SnowflakeShardingKeyGenerator keyGenerator(){
    return new SnowflakeShardingKeyGenerator();
  }


}
