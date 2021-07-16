package com.rhb.account.ctrl;

import com.rhb.account.service.AccountService;
import com.rhb.pojo.api.AccountApi;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/15 15:40
 */
@RestController
public class AccountOpenCtrl implements AccountApi {

  @Autowired
  AccountService accountService;

  @Override
  public void deduct(Long userId, BigDecimal money) {
    accountService.deduct(userId, money);
  }

}
