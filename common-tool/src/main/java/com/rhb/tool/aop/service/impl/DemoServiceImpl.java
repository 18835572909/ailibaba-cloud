package com.rhb.tool.aop.service.impl;

import com.rhb.tool.aop.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/13 15:34
 */
@Service
public class DemoServiceImpl implements DemoService {

  @Override
  public String ping(String ping) {
    return ping+":pong";
  }
}
