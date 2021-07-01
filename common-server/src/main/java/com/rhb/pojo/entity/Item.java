package com.rhb.pojo.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 14:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
  private String tid;
  private String title;
  private BigDecimal price;
  private String sku;
  private String spu;
  private String url;
}
