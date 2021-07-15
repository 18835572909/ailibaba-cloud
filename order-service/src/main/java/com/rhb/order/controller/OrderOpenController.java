package com.rhb.order.controller;

import cn.hutool.core.util.StrUtil;
import com.rhb.order.service.OrderService;
import com.rhb.pojo.api.OrderApi;
import com.rhb.pojo.entity.Order;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/15 15:35
 */
@RestController
public class OrderOpenController implements OrderApi {

  @Autowired
  OrderService orderService;

  @Override
  public Order defaultOrder() {
//    try {
//      Thread.sleep(50);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//    return Order.builder()
//        .oid(StrUtil.uuid())
//        .createTime(new Date())
//        .build();
    return null;
  }

  @Override
  public void createOrder(Long userId, Long sku, Integer count) {
    orderService.createOrder(userId,sku,count);
  }
}
