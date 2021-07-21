package com.rhb.tool.proxy.cglib;


import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Cglib动态代理：相对于jdk而言，实体类也可以代理
 *
 * @author renhuibo
 * @date 2021/7/19 17:34
 */
@Slf4j
public class DefaultMethodInterceptor implements MethodInterceptor {

  /**
   * 注意引入的MethodInterceptor所属包
   *
   * @param o Enhancer中的传递的代理的目标类
   * @param method 代理目标类调用的方法
   * @param objects 代理目标类调用方法的参数列表
   * @param methodProxy MethodProxy
   * @return 代理目标类调用的方法返回值
   * @throws Throwable 异常
   */
  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
      throws Throwable {
    log.info("Object-Class：{}",o.getClass().getSimpleName());
    log.info("Method-Name：{}",method.getName());
    log.info("Objects.length:{}",objects.length);
    for (Object object : objects) {
      log.info("param:{}",object);
    }
    Object o1 = methodProxy.invokeSuper(o, objects);
    log.info("返回信息：{}",o1);
    return o1;
  }
}
