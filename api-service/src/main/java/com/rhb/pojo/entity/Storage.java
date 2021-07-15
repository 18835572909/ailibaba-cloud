package com.rhb.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_storage")
public class Storage {
  @TableId(type = IdType.AUTO)
  private Long id;
  private Long productId;
  private Long total;
  private Long userd;
  private Long residue;
}
