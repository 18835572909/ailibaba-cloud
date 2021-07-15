package com.rhb.storage.ctrl;

import com.rhb.pojo.api.StorageApi;
import com.rhb.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/15 15:50
 */
@RestController
public class StorageOpenCtrl implements StorageApi {

  @Autowired
  StorageService storageService;

  @Override
  public void deduct(String sku, Integer count) {
    storageService.deduct(sku,count);
  }

}
