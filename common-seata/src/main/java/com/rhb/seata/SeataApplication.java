package com.rhb.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Seata样例服务
 *
 * @author renhuibo
 * @date 2021/7/15 14:00
 */
@EnableFeignClients
@SpringBootApplication
public class SeataApplication {
  public static void main(String[] args) {
    SpringApplication.run(SeataApplication.class,args);
  }
}
