package com.rhb.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 账单服务
 *
 * @author renhuibo
 * @date 2021/7/15 13:48
 */
@MapperScan(basePackages = {"com.rhb.account.mapper"})
@SpringBootApplication
public class AccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountApplication.class,args);
  }

}
