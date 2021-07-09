package com.rhb.gateway.controller;

import com.rhb.gateway.pojo.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/8 18:18
 */
@RestController
public class CommonController {

  @GetMapping("/fallback")
  public Response fallbackA() {
    Response response = new Response();
    response.setCode(100);
    response.setMsg("服务暂时不可用");
    return response;
  }

}
