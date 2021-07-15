package com.rhb.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.rhb.order.mapper.OrderMapper;
import com.rhb.order.service.AccountService;
import com.rhb.order.service.OrderService;
import com.rhb.pojo.entity.Order;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单实现类
 *
 * @author renhuibo
 * @date 2021/7/2 10:12
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {

  @Autowired
  AccountService accountService;

//  @SentinelResource(value = "findOrderById",
//      blockHandlerClass = ,
//      fallbackClass = ,
//
//  )
  @Override
  public Order findOrderByOid(String oid) {
    return null;
  }

  @Override
  public void createOrder(Long userId, Long sku, Integer count) {
//    accountService.deduct(userId,findSkuPrice(sku).multiply(new BigDecimal(count)));

    Order order = Order.builder().userId(userId).count(count).productId(sku)
        .status(0).money(findSkuPrice(sku).multiply(new BigDecimal(count)))
        .build();

    this.save(order);

    log.info("创建订单：{}",order.getId());
  }

  /**
   * 查询sku价格
   *
   * @param sku sku
   * @return 价格
   */
  private BigDecimal findSkuPrice(Long sku){
    ImmutableMap<Long, BigDecimal> skuMap = ImmutableMap.of(
        1L, new BigDecimal(56.3),
        2L, new BigDecimal(63.1),
        3L, new BigDecimal(23));
    return skuMap.get(sku);
  }

}
