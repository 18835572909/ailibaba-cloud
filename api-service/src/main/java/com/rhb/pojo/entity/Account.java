package com.rhb.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_account")
public class Account {
  @TableId(type = IdType.AUTO)
  private Long id;
  private Long userId;
  private BigDecimal total;
  private BigDecimal userd;
  private BigDecimal residue;
}
