package com.rhb.pojo.api;

import com.rhb.pojo.entity.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 15:44
 */
@RequestMapping("/order")
public interface OrderApi {

  @RequestMapping(value = "/default",method = RequestMethod.GET)
  Order defaultOrder();

  @RequestMapping(value = "/create",method = RequestMethod.POST)
  void createOrder(@RequestParam("userId") Long userId,@RequestParam("sku") Long sku,@RequestParam("count") Integer count);

}
