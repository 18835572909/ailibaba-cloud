package com.rhb.tool.aop.log;

import java.lang.reflect.Method;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 *
 * @author renhuibo
 * @date 2021/7/26 10:00
 */
@Slf4j
@Aspect
@Component
public class SysLogAop {

  @Pointcut(value = "@annotation(com.rhb.tool.aop.log.SysLog)")
  public void pointcut() {
  }

  public Object around(ProceedingJoinPoint pjp) {
    Object proceed = null;
    try {
      Class<?> cla = pjp.getTarget().getClass();
      MethodSignature signature = (MethodSignature) pjp.getSignature();
      Method method = cla.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
      SysLog logAnnotation = method.getAnnotation(SysLog.class);
      Logger logger = Logger.builder()
          .model(logAnnotation.model())
          .action(logAnnotation.action())
          .desc(logAnnotation.desc())
          .optime(new Date())
          .build();
      SysLogHelper.saveLog(logger);
      proceed = pjp.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return proceed;
  }

}
