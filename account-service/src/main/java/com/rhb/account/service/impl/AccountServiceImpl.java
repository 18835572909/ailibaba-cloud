package com.rhb.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhb.account.mapper.AccountMapper;
import com.rhb.account.service.AccountService;
import com.rhb.pojo.entity.Account;
import java.math.BigDecimal;
import java.util.List;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

  @Override
  public void deduct(Long userId, BigDecimal money) {
    LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Account::getUserId,userId);
    Account account = this.getOne(queryWrapper);

    account.setUsed(account.getUsed().add(money));
    account.setResidue(account.getResidue().subtract(money));

    this.saveOrUpdate(account);
    log.info("{} 扣除金额：{}",userId,money);
  }

}
