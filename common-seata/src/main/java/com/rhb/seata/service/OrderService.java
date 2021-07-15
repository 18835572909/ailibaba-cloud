package com.rhb.seata.service;

import com.rhb.pojo.api.OrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author renhuibo
 * @date 2021/7/15 14:39
 */
@FeignClient(value = "order-server")
public interface OrderService extends OrderApi {

}
