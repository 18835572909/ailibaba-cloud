package com.rhb.seata.ctrl;

import com.rhb.seata.service.SeataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/15 15:55
 */
@RestController
@RequestMapping("/seata")
public class SeataCtrl {

  @Autowired
  SeataService seataService;

  @RequestMapping("/purchase")
  public void purchase(String userId,String sku,Integer count){
    seataService.purchase(userId, sku, count);
  }

}
