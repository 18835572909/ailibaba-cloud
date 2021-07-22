package com.rhb.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/22 13:55
 */
@MapperScan(basePackages = {"com.rhb.sharding.mapper"})
@SpringBootApplication
public class ShardingApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShardingApplication.class,args);
  }

}
