package com.rhb.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Gateway提供Redis支持的限流实现配置
 *
 * @author renhuibo
 * @date 2021/7/8 13:48
 */
@Configuration
public class RateLimiterConfig {

  //基于路径进行限流
  @Bean(name = "pathKeyResolver")
  @Primary
  public KeyResolver pathKeyResolver() {
    return exchange -> Mono.just(exchange.getRequest().getPath().toString());
  }

  //基于ip进行限流
  @Bean(name = "ipKeyResolver")
  public KeyResolver ipKeyResolver() {
    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostName());
  }

  //根据请求参数进行限流
  @Bean(name = "userIdKeyResolver")
  public KeyResolver userIdKeyResolver() {
    return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
  }

}
