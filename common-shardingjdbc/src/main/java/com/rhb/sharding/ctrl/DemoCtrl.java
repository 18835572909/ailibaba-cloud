package com.rhb.sharding.ctrl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rhb.sharding.mapper.ItemMapper;
import com.rhb.sharding.mapper.OrderMapper;
import com.rhb.sharding.pojo.Item;
import com.rhb.sharding.pojo.Order;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/22 15:39
 */
@Slf4j
@RestController
public class DemoCtrl {

  @Autowired
  OrderMapper orderMapper;

  @Autowired
  ItemMapper itemMapper;

  @Resource(name = "keyGenerator")
  SnowflakeShardingKeyGenerator keyGenerator;

  @GetMapping("order/save")
  public void save(){
    for(int i=0;i<10;i++){
      Order order = Order.builder()
          .id((Long)keyGenerator.generateKey())
          .inserttime(new Date())
          .isactive(1)
          .orderId(10000 + i)
          .orderNo("NO" + 10000 + i)
          .userId(1000 + i)
          .updatetime(new Date())
          .build();
      orderMapper.insert(order);
    }
  }

  @GetMapping("order/selectList")
  public List<Order> selectList(){
    LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.lt(Order::getInserttime,new Date());
    return orderMapper.selectList(queryWrapper);
  }

  @GetMapping("item/save")
  public void saveItem(){
    Long[] oids = {625043559334019072L,625043933382049792L,625043934833278976L,625043558495158272L,
        625043934971691009L,625043559535345665L,625043934485151745L};

    for(int i=0;i<10;i++){
      Item item = Item.builder()
          .id((Long)keyGenerator.generateKey())
          .itemId(20000+i)
          .itemNo("200"+i)
          .inserttime(new Date())
          .updatetime(new Date())
          .payName("abcd")
          .isactive(1)
          .userId(i)
          .build();

      if(i>oids.length-1){
        item.setOid(oids[0]);
      }else{
        item.setOid(oids[i]);
      }

      log.info(JSONUtil.parseObj(item).toString());

      itemMapper.insert(item);
    }
  }

  @GetMapping("item/selectList")
  public List<Item> selectListItem(){
    LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.lt(Item::getInserttime,new Date());
    queryWrapper.orderByDesc(Item::getId);
    return itemMapper.selectList(queryWrapper);
  }

  @GetMapping("item/findOne")
  public List<Item> findOneItem(Long orderId){
    return itemMapper.selectItemByOrderId(orderId);
  }

}
