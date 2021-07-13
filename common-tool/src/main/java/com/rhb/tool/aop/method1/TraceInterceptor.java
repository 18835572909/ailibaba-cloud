package com.rhb.tool.aop.method1;

import java.lang.reflect.Method;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * AOP方法1： 使用MethodAInterceptor+DefaultPointcutAdvisor
 *
 * @author renhuibo
 * @date 2021/7/13 13:55
 */
@Slf4j
public class TraceInterceptor implements MethodInterceptor {

  @Nullable
  @Override
  public Object invoke(@Nonnull MethodInvocation methodInvocation) throws Throwable {
    Method method = methodInvocation.getMethod();
    Object[] arguments = methodInvocation.getArguments();
    log.info("方法前置...");
    Object proceed = methodInvocation.proceed();
    log.info("方法后置...");
    return proceed;
  }
}
