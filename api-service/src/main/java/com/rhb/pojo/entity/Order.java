package com.rhb.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rhb.pojo.enums.OrderStatus;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 订单实体类
 *
 * @author renhuibo
 * @date 2021/7/1 14:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order {
  @TableId(type = IdType.AUTO)
  private Long id;
  private Long productId;
  private Integer count;
  private Long userId;
  private BigDecimal money;
  /**
   * 订单状态: 0 创建中 1:已完结
   */
  private OrderStatus status;
}

