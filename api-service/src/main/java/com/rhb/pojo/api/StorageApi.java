package com.rhb.pojo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 仓储
 *
 * @author renhuibo
 * @date 2021/7/15 14:24
 */
@RequestMapping("/storage")
public interface StorageApi {

  @RequestMapping(value = "/deduct",method = RequestMethod.POST)
  void deduct(@RequestParam("sku") String sku,@RequestParam("count") Integer count);

}
