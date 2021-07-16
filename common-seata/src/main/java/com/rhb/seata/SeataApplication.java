package com.rhb.seata;

import com.baomidou.mybatisplus.autoconfigure.IdentifierGeneratorAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Seata样例服务
 *
 * @author renhuibo
 * @date 2021/7/15 14:00
 */
@EnableFeignClients
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    MybatisPlusAutoConfiguration.class,
    IdentifierGeneratorAutoConfiguration.class})
public class SeataApplication {
  public static void main(String[] args) {
    SpringApplication.run(SeataApplication.class,args);
  }
}
