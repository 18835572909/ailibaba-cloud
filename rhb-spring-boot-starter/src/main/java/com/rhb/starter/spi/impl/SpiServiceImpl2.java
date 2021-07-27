package com.rhb.starter.spi.impl;

import com.rhb.starter.spi.SpiService;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/27 10:17
 */
@Slf4j
public class SpiServiceImpl2 implements SpiService {

  @Override
  public void sayHello(){
    log.info("2-hello");
  }

}
