package com.rhb.order.controller;

import cn.hutool.core.util.StrUtil;
import com.rhb.order.service.ItemService;
import com.rhb.order.service.OrderService;
import com.rhb.pojo.api.OrderApi;
import com.rhb.pojo.entity.Item;
import com.rhb.pojo.entity.Order;
import java.util.Date;
import org.checkerframework.framework.qual.RequiresQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 14:09
 */
@RestController
@RequestMapping("/v1/order")
public class OrderController implements OrderApi {

  @Autowired
  ItemService itemService;
  @Autowired
  OrderService orderService;

  @Override
  public Order defaultOrder() {
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return Order.builder()
        .oid(StrUtil.uuid())
        .createTime(new Date())
        .build();
  }

  @RequestMapping("/defaultItem")
  public Item defaultItem(){
    return itemService.defaultItem();
  }

  @RequestMapping("/findByOid")
  public Order findByOid(String oid){
    return orderService.findOrderByOid(oid);
  }
}
