package com.rhb.tool.aop.ctrl;

import com.rhb.tool.aop.method2.annotation.Aspect2;
import com.rhb.tool.aop.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/13 15:35
 */
@RestController
public class DemoCtrl {

  @Autowired
  DemoService demoService;

  @GetMapping("ping")
  public String ping(String ping){
    return demoService.ping(ping);
  }

  @Aspect2
  @GetMapping("ping2")
  public String ping2(String ping){
    return demoService.ping(ping);
  }

}
