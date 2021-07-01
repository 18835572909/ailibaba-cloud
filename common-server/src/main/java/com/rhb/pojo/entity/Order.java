package com.rhb.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 14:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private String oid;
  private Date createTime;
}
