package com.rhb.item.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/2 17:56
 */
@RestController
@RequestMapping("/t")
public class DemoController {

  @Value("${spring.cloud.sentinel.transport.dashboard:localhost:8419}")
  private String sentinelUrl;

  @GetMapping("/sentinel")
  public String sentinel(){
    return sentinelUrl;
  }

}
