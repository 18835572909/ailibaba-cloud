package com.rhb.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 仓储服务
 *
 * @author renhuibo
 * @date 2021/7/15 13:55
 */
@MapperScan(basePackages = {"com.rhb.storage.mapper"})
@SpringBootApplication
public class StorageApplication {

  public static void main(String[] args) {
    SpringApplication.run(StorageApplication.class,args);
  }

}
