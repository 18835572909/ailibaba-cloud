package com.rhb.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rhb.pojo.entity.Order;

/**
 * 订单服务
 *
 * @author renhuibo
 * @date 2021/7/2 10:11
 */
public interface OrderService extends IService<Order> {

  /**
   * 查询订单
   *
   * @param oid 订单id
   * @return 订单详情
   */
  Order findOrderByOid(String oid);

  /**
   * 创建订单
   *
   * @param userId 用户id
   * @param sku sku
   * @param count 商品数量
   */
  void createOrder(Long userId, Long sku, Integer count);

  /**
   * 测试状态机的使用： StateMachineConfigurerAdapter
   * 支付
   * @param id 订单id
   */
  void pay(int id);

  /**
   * 测试状态机的使用： StateMachineConfigurerAdapter
   * 发货
   * @param id 订单id
   */
  void deliver(int id);

  /**
   * 测试状态机的使用： StateMachineConfigurerAdapter
   * 送达
   * @param id 订单id
   */
  void receive(int id);
}
