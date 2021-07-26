package com.rhb.starter.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/24 18:02
 */
@ConditionalOnClass(Redisson.class) // 条件装配
// 关联 属性配置类
@EnableConfigurationProperties(RedissonProperties.class)
@Configuration
public class RedissonAutoConfiguration {
  @Bean
  public RedissonClient redissonClient(RedissonProperties redissonProperties){
    Config config = new Config();
    String prefix = "redis://";
    if(redissonProperties.isSsl()){
      prefix = "rediss://";
    }
    // 单节点连接配置
    config.useSingleServer()
        .setAddress(prefix+redissonProperties.getHost()+":"+redissonProperties.getPort())
        .setPassword(redissonProperties.getPassword())
        .setConnectTimeout(redissonProperties.getTimeout());
    return Redisson.create(config);
  }

}
