package com.rhb.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/24 18:01
 */
@Data
@ConfigurationProperties(prefix = "bobo.redisson")
public class RedissonProperties {
  private String host = "localhost";
  private int port = 6379;
  private int timeout = 0;
  private boolean ssl = false;
  private String password = "";
}
