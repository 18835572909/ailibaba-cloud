package com.rhb.seata.service.impl;

import com.rhb.seata.service.OrderService;
import com.rhb.seata.service.SeataService;
import com.rhb.seata.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现类
 *
 * @author renhuibo
 * @date 2021/7/15 14:23
 */
@Slf4j
@Service
public class SeataServiceImpl implements SeataService {

  @Autowired
  OrderService orderService;
  @Autowired
  StorageService storageService;

  @GlobalTransactional
  @Override
  public void purchase(Long userId, Long sku, Integer count) {
    storageService.deduct(sku,count);
    orderService.createOrder(userId,sku,count);
    log.info("购买成功");
  }

}
