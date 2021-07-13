package com.rhb.tool.aop.method2.aspect;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/13 16:16
 */
//@Configuration
public class Default2PointcutAdvisorWrap {

  @Bean
  // @Primary 只对接口的多个实现有效
  public DefaultPointcutAdvisor defaultPointcutAdvisor(){


    JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
    pointcut.setPattern("com.rhb.tool.aop.ctrl.*");

    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
    advisor.setAdvice(new Trace2Interceptor());
    advisor.setPointcut(pointcut);

    return advisor;
  }

}
