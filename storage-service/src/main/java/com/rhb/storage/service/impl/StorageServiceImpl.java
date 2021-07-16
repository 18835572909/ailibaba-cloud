package com.rhb.storage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhb.pojo.entity.Storage;
import com.rhb.storage.mapper.StorageMapper;
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
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

  @Override
  public void deduct(Long sku, Integer count) {
    LambdaQueryWrapper<Storage> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Storage::getProductId,sku);

    Storage storage = this.getOne(queryWrapper);
    storage.setUserd(storage.getUserd()+count);
    storage.setResidue(storage.getResidue()-count);

    this.saveOrUpdate(storage);
    log.info("{} 扣除{}个",sku,count);
  }

}
