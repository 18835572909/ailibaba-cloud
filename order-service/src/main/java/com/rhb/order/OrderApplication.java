package com.rhb.order;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 11:23
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.rhb.order.mapper"})
public class OrderApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class,args);
    log.info("Order Server start ... ");
  }
}
