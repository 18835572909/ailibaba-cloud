package com.rhb.pojo.api;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 账单
 *
 * @author renhuibo
 * @date 2021/7/15 14:26
 */
@RequestMapping("/account")
public interface AccountApi {

  @RequestMapping(value = "/deduct",method = RequestMethod.POST)
  void deduct(@RequestParam("userId") String userId, @RequestParam("money") BigDecimal money);

}
