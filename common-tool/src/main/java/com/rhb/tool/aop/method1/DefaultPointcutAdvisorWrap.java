package com.rhb.tool.aop.method1;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/13 14:43
 */
//@Configuration
public class DefaultPointcutAdvisorWrap {

  private static final String EXPRESSION = "execution(* com.rhb.tool.aop.service.impl..*.*(..))";

  @Bean
  public DefaultPointcutAdvisor defaultPointcutAdvisor(){
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression(EXPRESSION);

    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
    advisor.setPointcut(pointcut);
    advisor.setAdvice(new TraceInterceptor());

    return advisor;
  }


}
