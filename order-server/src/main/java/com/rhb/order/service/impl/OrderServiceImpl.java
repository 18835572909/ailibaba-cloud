package com.rhb.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.rhb.order.service.OrderService;
import com.rhb.pojo.entity.Order;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/2 10:12
 */
@Service
public class OrderServiceImpl implements OrderService {

  @SentinelResource(value = "findOrderById")
  @Override
  public Order findOrderByOid(String oid) {
    return Order.builder()
        .oid(oid)
        .createTime(new Date())
        .build();
  }

}
