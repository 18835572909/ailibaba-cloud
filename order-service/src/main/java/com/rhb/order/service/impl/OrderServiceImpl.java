package com.rhb.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.rhb.order.mapper.OrderMapper;
import com.rhb.order.service.AccountService;
import com.rhb.order.service.OrderService;
import com.rhb.pojo.entity.Order;
import com.rhb.pojo.enums.OrderStatus;
import com.rhb.pojo.enums.OrderStatusChangeEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
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

  @Autowired
  StateMachine<OrderStatus,OrderStatusChangeEvent> orderStatusChangeEventStateMachine;

  @Autowired
  StateMachinePersister<OrderStatus,OrderStatusChangeEvent,Order> persister;

  private Map<Integer, Order> orders = new HashMap<>();

  @PostConstruct
  public void init(){
    orders.put(1,Order.builder().id(1L).count(100).build());
  }

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
    accountService.deduct(userId,findSkuPrice(sku).multiply(new BigDecimal(count)));

    Order order = Order.builder().userId(userId).count(count).productId(sku)
        .status(OrderStatus.FINISH).money(findSkuPrice(sku).multiply(new BigDecimal(count)))
        .build();

    this.save(order);
    log.info("创建订单：{}",order.getId());
  }

  @Override
  public void pay(int id) {
    Order order = orders.get(id);

    System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试支付 id=" + id);
    Message<OrderStatusChangeEvent> message = MessageBuilder
        .withPayload(OrderStatusChangeEvent.PAYED).setHeader("order", order).build();

    if(!sendEvent(message,order)){
      System.out.println("threadName=" + Thread.currentThread().getName() + " 支付失败, 状态异常 id=" + id);
    }
  }

  @Override
  public void deliver(int id) {
    Order order = orders.get(id);

    System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试发货 id=" + id);
    Message<OrderStatusChangeEvent> message = MessageBuilder
        .withPayload(OrderStatusChangeEvent.DELIVERY).setHeader("order", order).build();

    if(!sendEvent(message,order)){
      System.out.println("threadName=" + Thread.currentThread().getName() + " 发货失败，状态异常 id=" + id);
    }
  }

  @Override
  public void receive(int id) {
    Order order = orders.get(id);

    System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试收货 id=" + id);
    Message<OrderStatusChangeEvent> message = MessageBuilder
        .withPayload(OrderStatusChangeEvent.RECEIVED).setHeader("order", order).build();

    if(!sendEvent(message,order)){
      System.out.println("threadName=" + Thread.currentThread().getName() + " 收货失败，状态异常 id=" + id);
    }
  }

  /**
   * 查询sku价格
   *
   * @param sku sku
   * @return 价格
   */
  private BigDecimal findSkuPrice(Long sku){
    ImmutableMap<Long, BigDecimal> skuMap = ImmutableMap.of(
        100001L, new BigDecimal(56.3),
        100002L, new BigDecimal(63.1),
        100003L, new BigDecimal(23));
    return skuMap.get(sku);
  }

  private synchronized boolean sendEvent(Message<OrderStatusChangeEvent> message,Order order){
    boolean result = false;
    try{
      orderStatusChangeEventStateMachine.start();
      Thread.sleep(1000);
      result = orderStatusChangeEventStateMachine.sendEvent(message);
//      persister.persist(orderStatusChangeEventStateMachine,order);
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      orderStatusChangeEventStateMachine.stop();
    }
    return result;
  }
}
