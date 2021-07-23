package com.rhb.sharding.util;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/23 14:30
 */
public class DruidUtil {

  public static void main(String[] args) throws Exception{
    String publickey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANVQeqGqGs/k5Pb+iT7YrDz2BBl0sTdaqaTvdZZbR2RErGoIF7Fjv9+StJjValAc077tGis0WDU/JIplQ8iKRs8CAwEAAQ==";
    String password = "wGbptkB0hdoh8lgqxm1wtI2EX2LibkWmCOtJ+UGYPRN1GmwPs63ld/1axGDj+c14ypSX7zEcwaxd0Tq/B8cZqw==";
    String pwd = ConfigTools.decrypt(publickey, password);
    System.out.println(pwd);
  }


}
