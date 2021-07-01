package com.rhb.pojo.api;

import com.rhb.pojo.entity.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 15:44
 */
@RequestMapping("/v1/order")
public interface OrderApi {

  @RequestMapping(value = "/default",method = RequestMethod.GET)
  Order defaultOrder();

}
