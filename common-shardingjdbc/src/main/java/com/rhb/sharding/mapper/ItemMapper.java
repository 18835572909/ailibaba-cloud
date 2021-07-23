package com.rhb.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhb.sharding.pojo.Item;
import java.util.List;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/23 11:47
 */
public interface ItemMapper extends BaseMapper<Item> {

  /**
   * 订单查询查询商品
   *
   * @param orderId 订单id
   * @return 商品信息
   */
  List<Item> selectItemByOrderId(Long orderId);

}
