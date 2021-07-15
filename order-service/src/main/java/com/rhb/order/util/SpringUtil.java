package com.rhb.order.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/5/26 10:23
 */
@Component
public class SpringUtil implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @SuppressWarnings("unchecked")
  public <T> T getBean(String name) throws BeansException {
    return (T) applicationContext.getBean(name);
  }

  @SuppressWarnings("unchecked")
  public <T> T getBean(Class<?> clz) throws BeansException {
    return (T) applicationContext.getBean(clz);
  }

}
