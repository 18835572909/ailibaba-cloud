package com.rhb.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 14:37
 */
@RefreshScope
@RestController
@RequestMapping("nacos")
public class NacosConfigController {

  @Value("${ping:no}")
  private String ping;

  @Value("${jdbc.url:null}")
  private String jdbcUrl;

  @Value("${spring.cloud.sentinel.datasource.ds.nacos.dataId:application}")
  private String dataId;

  @RequestMapping("ping")
  public String ping(){
    return ping;
  }

  @RequestMapping("url")
  public String url(){
    return jdbcUrl;
  }

  @GetMapping("sentinel")
  public String sentinel(){
    return dataId;
  }

}
