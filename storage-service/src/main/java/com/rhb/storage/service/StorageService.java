package com.rhb.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rhb.pojo.entity.Storage;

/**
 * 仓储服务
 *
 * @author renhuibo
 * @date 2021/7/15 13:56
 */
public interface StorageService extends IService<Storage> {

  /**
   * 仓储扣除
   *
   * @param sku sku
   * @param count 扣除数量
   */
  void deduct(Long sku,Integer count);

}
