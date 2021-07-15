package com.rhb.storage.service.impl;

import com.rhb.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 仓储服务实现类
 *
 * @author renhuibo
 * @date 2021/7/15 14:13
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

  @Override
  public void deduct(String sku, Integer count) {
    log.info("{} 扣除{}个",sku,count);
  }

}
