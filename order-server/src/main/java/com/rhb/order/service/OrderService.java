package com.rhb.order.service;

import com.rhb.pojo.entity.Order;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/2 10:11
 */
public interface OrderService {

  Order findOrderByOid(String oid);

}
