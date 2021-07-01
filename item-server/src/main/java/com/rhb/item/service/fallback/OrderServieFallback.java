package com.rhb.item.service.fallback;

import com.rhb.item.service.OrderService;
import com.rhb.pojo.entity.Order;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 15:58
 */
@Slf4j
public class OrderServieFallback implements OrderService {
  @Override
  public Order defaultOrder() {
    log.info("order-server#defaultOrder exception ...");
    return null;
  }
}
