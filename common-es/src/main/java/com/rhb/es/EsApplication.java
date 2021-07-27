package com.rhb.es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/27 18:10
 */
@Slf4j
@SpringBootApplication
public class EsApplication {

  public static void main(String[] args) {
    SpringApplication.run(EsApplication.class, args);
    log.info("启动成功");
  }

}
