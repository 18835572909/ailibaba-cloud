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
import org.springframework.web.bind.annotation.GetMapping;
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
public class OrderController{

  @Autowired
  ItemService itemService;
  @Autowired
  OrderService orderService;

  @RequestMapping("/defaultItem")
  public Item defaultItem(){
    return itemService.defaultItem();
  }

  @RequestMapping("/findByOid")
  public Order findByOid(String oid){
    return orderService.findOrderByOid(oid);
  }

  @GetMapping("/pay")
  public void pay(int id){
    orderService.pay(id);
  }

  @GetMapping("/deliver")
  public void deliver(int id){
    orderService.deliver(id);
  }

  @GetMapping("/receive")
  public void receive(int id) {
    orderService.receive(id);
  }
}
