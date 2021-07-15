package com.rhb.item.service;

import com.rhb.item.service.fallback.OrderServieFallback;
import com.rhb.pojo.api.OrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 15:46
 */
@FeignClient(value="order-server",fallback = OrderServieFallback.class)
public interface OrderService extends OrderApi {

}
