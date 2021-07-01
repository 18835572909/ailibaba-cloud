package com.rhb.pojo.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Collection;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义fegin的请求拦截器
 *
 * @author renhuibo
 * @date 2021/7/1 16:17
 */
@Slf4j
public class FooRequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    log.info("FooRequestInterceptor ... ");
    Map<String, Collection<String>> headers = requestTemplate.headers();
    headers.forEach((k,v)->{
      log.info("{}:{}",k,v);
    });

  }
}
