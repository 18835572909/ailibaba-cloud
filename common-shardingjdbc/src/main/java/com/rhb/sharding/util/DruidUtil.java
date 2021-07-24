package com.rhb.sharding.util;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.extern.slf4j.Slf4j;

/**
 * Druid工具类
 *
 * @author renhuibo
 * @date 2021/7/23 14:30
 */
@Slf4j
public class DruidUtil {

  /**
   * Druid密码解密
   *
   * String publickey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANVQeqGqGs/k5Pb+iT7YrDz2BBl0sTdaqaTvdZZbR2RErGoIF7Fjv9+StJjValAc077tGis0WDU/JIplQ8iKRs8CAwEAAQ==";
   * String password = "wGbptkB0hdoh8lgqxm1wtI2EX2LibkWmCOtJ+UGYPRN1GmwPs63ld/1axGDj+c14ypSX7zEcwaxd0Tq/B8cZqw==";
   *
   * @param publickey 公钥
   * @param password 密码
   * @return 明文密码
   */
  public static String decrpt(String publickey,String password){
    try {
      return ConfigTools.decrypt(publickey, password);
    } catch (Exception e) {
      log.info("解密错误 ...");
      return "";
    }
  }


}
