package com.rhb.sharding.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * @author renhuibo
 * @date 2021/7/22 15:44
 */
@Data
@Builder
@TableName("t_order")
public class Order {
  @TableId(type = IdType.AUTO)
  private Long id;
  private Integer userId;
  private Integer orderId;
  private String orderNo;
  private Integer isactive;
  private Date inserttime;
  private Date updatetime;
}
