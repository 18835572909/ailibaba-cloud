package com.rhb.tool.aop.method2.aspect;

import com.rhb.tool.aop.method2.annotation.Aspect2;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/13 16:10
 */
@Slf4j
public class Trace2Interceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    Aspect2 annotation = AnnotationUtils
        .findAnnotation(methodInvocation.getMethod(), Aspect2.class);

    if(annotation==null){
      return methodInvocation.proceed();
    }

    log.info("前置方法...");
    Object proceed = methodInvocation.proceed();
    log.info("后置方法...");

    return proceed;
  }
}
