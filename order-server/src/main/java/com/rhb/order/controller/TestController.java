package com.rhb.order.controller;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.rhb.order.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  检测nacos中json文件是否可以读到。
 *
 *  令人诧异的是：在没有配置namespace的时候，竟然是可以读到的，但是流控规则不生效。
 *
 * @author renhuibo
 * @date 2021/7/6 15:59
 */
@Slf4j
@RestController
public class TestController {

  @Autowired
  SpringUtil springUtil;

  @GetMapping("hello")
  public String hello() throws NacosException {
    NacosConfigManager configManager = springUtil.getBean(NacosConfigManager.class);
    String json = configManager.getConfigService()
        .getConfig("order-server-sentinel", "SpringCloudAlibaba", 1000);
    log.info(json);
    return "hello";
  }

  @GetMapping("timeout")
  public String timeout() throws Exception {
    Thread.sleep(50000);
    return "hello";
  }
}
