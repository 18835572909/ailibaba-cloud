package com.rhb.tool.aop.log;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志使用样例
 *
 * @author renhuibo
 * @date 2021/7/26 11:08
 */
@RestController
@RequestMapping("testLog")
public class LoggerSampleController {

  @GetMapping("aop")
  @SysLog(model = "测试",action = "aop测试",desc = "测试aop日志")
  public void aopTest(){

  }

  @GetMapping("helper")
  public void helperTest(){
    SysLogHelper.saveSimpleLogger("测试helper日志");
  }

}
