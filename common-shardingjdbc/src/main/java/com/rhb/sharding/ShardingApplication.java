package com.rhb.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/22 13:55
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.rhb.sharding.mapper"})
@EntityScan(basePackages = {"com.rhb.sharding.pojo"})
public class ShardingApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShardingApplication.class,args);
  }

}
