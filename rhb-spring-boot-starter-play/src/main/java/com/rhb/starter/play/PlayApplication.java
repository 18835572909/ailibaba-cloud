package com.rhb.starter.play;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author renhuibo
 * @date 2021/7/24 18:22
 */
@Slf4j
@SpringBootApplication
public class PlayApplication {

  public static void main(String[] args) {
    /*
    // 基于配置文件的上下文容器
    ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
    // 基于注解的上下文容器
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(PlayApplication.class);
    */
    /**
     * ApplicationContext子类：
     *    ConfigurableApplicationContext、AnnotationConfigApplicationContext、ClassPathXmlApplicationContext
     * run方法返回的是一个ApplicationContext： 说明这是ApplicationContext的初始化过程【IOC】
     */
    ConfigurableApplicationContext run = SpringApplication.run(PlayApplication.class, args);
    log.info("启动成功...");
  }

}
