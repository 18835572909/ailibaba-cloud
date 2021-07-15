package com.rhb.account.service.impl;

import com.rhb.account.service.AccountService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 账单服务实现类
 *
 * @author renhuibo
 * @date 2021/7/15 14:10
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

  @Override
  public void deduct(String userId, BigDecimal money) {
    log.info("{} 扣除金额：{}",userId,money);
  }

}
