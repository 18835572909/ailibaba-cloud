package com.rhb.storage.service;

/**
 * 仓储服务
 *
 * @author renhuibo
 * @date 2021/7/15 13:56
 */
public interface StorageService {

  /**
   * 仓储扣除
   *
   * @param sku sku
   * @param count 扣除数量
   */
  void deduct(String sku,Integer count);

}
