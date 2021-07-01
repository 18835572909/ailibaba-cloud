package com.rhb.order.controller;

import cn.hutool.core.util.StrUtil;
import com.rhb.pojo.api.OrderApi;
import com.rhb.pojo.entity.Order;
import java.util.Date;
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
}
