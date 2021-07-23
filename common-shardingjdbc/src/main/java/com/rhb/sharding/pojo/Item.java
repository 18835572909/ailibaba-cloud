package com.rhb.sharding.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/23 11:46
 */
@Data
@Builder
@TableName("t_item")
public class Item {
  private Long id;
  private Integer userId;
  private Integer itemId;
  private String itemNo;
  private Long oid;
  private Integer isactive;
  private Date inserttime;
  private Date updatetime;
}
