package com.rhb.sharding.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rhb.sharding.mapper.OrderMapper;
import com.rhb.sharding.pojo.Order;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
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
@RestController
public class DemoCtrl {

  @Autowired
  OrderMapper orderMapper;

  @Resource(name = "keyGenerator")
  SnowflakeShardingKeyGenerator keyGenerator;

  @GetMapping("save")
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

  @GetMapping("selectList")
  public List<Order> selectList(){
    LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.lt(Order::getInserttime,new Date());
    return orderMapper.selectList(queryWrapper);
  }

}
