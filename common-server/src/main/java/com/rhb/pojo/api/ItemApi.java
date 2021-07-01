package com.rhb.pojo.api;

import com.rhb.pojo.entity.Item;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 15:42
 */
@RequestMapping("/v1/item")
public interface ItemApi {

  @RequestMapping(value = "/default",method = RequestMethod.GET)
  public Item defaultItem();

}
