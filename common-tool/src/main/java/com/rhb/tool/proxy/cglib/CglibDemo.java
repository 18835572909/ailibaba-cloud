package com.rhb.tool.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;

/**
 * 测试主类
 *
 * @author renhuibo
 * @date 2021/7/19 17:51
 */
@Slf4j
public class CglibDemo {

  public static void main(String[] args) {
    DefaultMethodInterceptor interceptor = new DefaultMethodInterceptor();
    Object o = Enhancer.create(CglibService.class, interceptor);

    if(o instanceof CglibService){
      CglibService service = (CglibService) o ;
      log.info("代理成功...");
      service.say("hello");
    }
  }

}
