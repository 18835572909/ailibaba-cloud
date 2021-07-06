package com.rhb.order.handler;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

/**
 * 使用源码引入，发现FlowRuleManager不在容器中
 *
 * @author renhuibo
 * @date 2021/7/6 17:29
 */
@Slf4j
//@Component
public class SentinelNacosConfig implements ApplicationRunner {

  /**
   * 默认值取得官网给的demo
   */
  static String remoteAddress = "localhost:8848";
  static String groupId = "Sentinel_Demo";
  static String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";

  @Value("${spring.cloud.sentinel.datasource.ds.nacos.server-addr}")
  public static void setRemoteAddress(String remoteAddress) {
    SentinelNacosConfig.remoteAddress = remoteAddress;
  }

  @Value("${spring.cloud.sentinel.datasource.ds.nacos.groupId}")
  public static void setGroupId(String groupId) {
    SentinelNacosConfig.groupId = groupId;
  }

  @Value("${spring.cloud.sentinel.datasource.ds.nacos.dataId}")
  public static void setDataId(String dataId) {
    SentinelNacosConfig.dataId = dataId;
  }

  @Bean
  public FlowRuleManager flowRuleManager(){
    return new FlowRuleManager();
  }

  public void loadRules(FlowRuleManager flowRuleManager) {
    ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
    FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    log.info("Sentinel加载nacos配置成功...");
  }

  @Autowired
  FlowRuleManager flowRuleManager;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    loadRules(flowRuleManager);
  }
}
