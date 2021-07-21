package com.rhb.tool.proxy.cglib;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理目标类
 *
 * @author renhuibo
 * @date 2021/7/19 17:54
 */
@Slf4j
public class CglibService {

  public String say(String say){
    log.info("say:{}",say);
    return say;
  }

}
