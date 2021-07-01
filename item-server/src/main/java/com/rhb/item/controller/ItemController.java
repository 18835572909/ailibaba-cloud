package com.rhb.item.controller;

import cn.hutool.core.util.StrUtil;
import com.rhb.item.service.OrderService;
import com.rhb.pojo.api.ItemApi;
import com.rhb.pojo.entity.Item;
import com.rhb.pojo.entity.Order;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 15:17
 */
@RestController
@RequestMapping("/v1/item")
public class ItemController implements ItemApi {

  @Autowired
  OrderService orderService;

  @Override
  @RequestMapping("defaultItem")
  public Item defaultItem(){
    return Item.builder()
        .tid(StrUtil.uuid())
        .price(new BigDecimal(56.2))
        .sku("202107011518")
        .spu("rhb202107011518")
        .title("大头皮鞋")
        .url("http://localhost:1007/defaultItem")
        .build();
  }

  @RequestMapping("/defaultOrder")
  public Order defaultOrder(){
    return orderService.defaultOrder();
  }
}
