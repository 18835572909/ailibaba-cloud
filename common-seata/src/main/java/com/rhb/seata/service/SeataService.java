package com.rhb.seata.service;

/**
 * seata测试样例
 *
 * @author renhuibo
 * @date 2021/7/15 14:03
 */
public interface SeataService {

  /**
   * 用户下单
   *
   * @param userId 用户id
   * @param sku sku
   * @param count 数量
   */
  void purchase(Long userId,Long sku,Integer count);

}
