package com.rhb.admin;

import lombok.extern.slf4j.Slf4j;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/10 14:35
 */
@Slf4j
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(AdminServerApplication.class,args);
    log.info("Spring Admin Server started ...");
  }
}
