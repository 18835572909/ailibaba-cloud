package com.rhb.tool.aop.method3;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/13 16:24
 */
@Slf4j
@Aspect
@Component
public class AspectPointcut {

  @Pointcut(value = "execution(* com.rhb.tool.aop.service.impl..*.*(..))")
  public void pointcut(){}

  @Before(value = "pointcut()")
  public void before(JoinPoint joinPoint){
    log.info("method3:前置通知");
  }

  @Around(value = "pointcut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("method3:around:前置处理");
    Object proceed = joinPoint.proceed();
    log.info("method3:around:后置处理");
    return proceed;
  }
}
