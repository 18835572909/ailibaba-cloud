package com.rhb.item.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.rhb.item.service.ItemService;
import com.rhb.pojo.entity.Item;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/2 11:41
 */
@Service
public class ItemServiceImpl implements ItemService {

  @SentinelResource(value = "findItemByTid")
  @Override
  public Item findItemByTid(String tid) {
    return Item.builder()
        .tid(tid)
        .price(new BigDecimal(56.2))
        .sku("202107011518")
        .spu("rhb202107011518")
        .title("大头皮鞋")
        .url("http://localhost:1007/defaultItem")
        .build();
  }
}
