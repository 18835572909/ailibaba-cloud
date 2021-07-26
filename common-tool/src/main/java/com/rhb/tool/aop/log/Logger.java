package com.rhb.tool.aop.log;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * 日志实体
 *
 * @author renhuibo
 * @date 2021/7/26 10:09
 */
@Data
@Builder
public class Logger {
  private String model;
  private String action;
  private String desc;
  private Date optime;
  private String operator;
}
