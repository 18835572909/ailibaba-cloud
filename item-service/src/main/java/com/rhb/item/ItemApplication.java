package com.rhb.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 15:15
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ItemApplication {

  public static void main(String[] args) {
    SpringApplication.run(ItemApplication.class,args);
    log.info("Item Server Start ... ");
  }

}
