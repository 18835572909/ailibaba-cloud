package com.rhb.order.service.fallback;

import com.rhb.order.service.ItemService;
import com.rhb.pojo.entity.Item;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 16:01
 */
@Slf4j
public class ItemServiceFallback implements ItemService {

  @Override
  public Item defaultItem() {
    log.info("item-server#defaultItem exception ...");
    return null;
  }

}
