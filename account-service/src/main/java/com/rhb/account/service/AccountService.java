package com.rhb.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rhb.pojo.entity.Account;
import java.math.BigDecimal;

/**
 * 账单服务
 *
 * @author renhuibo
 * @date 2021/7/15 13:49
 */
public interface AccountService extends IService<Account> {

  /**
   * 用户账户扣除
   *
   * @param userId 用户id
   * @param money 扣除金额
   */
  void deduct(Long userId, BigDecimal money);

}
