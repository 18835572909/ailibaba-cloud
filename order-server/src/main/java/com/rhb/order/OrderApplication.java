package com.rhb.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 11:23
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class OrderApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class,args);
    log.info("Order Server start ... ");
  }
}
