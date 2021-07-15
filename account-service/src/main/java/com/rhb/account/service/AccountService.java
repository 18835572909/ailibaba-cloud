package com.rhb.account.service;

import java.math.BigDecimal;

/**
 * 账单服务
 *
 * @author renhuibo
 * @date 2021/7/15 13:49
 */
public interface AccountService {

  /**
   * 用户账户扣除
   *
   * @param userId 用户id
   * @param money 扣除金额
   */
  void deduct(String userId, BigDecimal money);

}
