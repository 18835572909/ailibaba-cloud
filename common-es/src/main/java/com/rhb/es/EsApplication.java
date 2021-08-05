package com.rhb.es;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

  @Bean
  public JestClient jestClient(){
    JestClientFactory factory = new JestClientFactory();
    factory.setHttpClientConfig(new HttpClientConfig
        .Builder("http://124.71.80.133:9202")
        .multiThreaded(true)
        .build());
    return factory.getObject();
  }

}
