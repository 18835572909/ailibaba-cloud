package com.rhb.order.service;

import com.rhb.pojo.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author renhuibo
 * @date 2021/7/15 14:30
 */
@FeignClient(value = "account-server")
public interface AccountService extends AccountApi {
}
