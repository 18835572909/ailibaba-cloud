package com.rhb.es.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/8/5 10:20
 */
@Data
@Builder
public class User {
  private String username;
  private String password;
  private int age;
  private int sex;
  private String city;
}
