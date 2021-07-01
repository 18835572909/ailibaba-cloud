package com.rhb.order.service;

import com.rhb.order.service.fallback.ItemServiceFallback;
import com.rhb.pojo.api.ItemApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 16:00
 */
@FeignClient(value="order-server",fallback = ItemServiceFallback.class)
public interface ItemService extends ItemApi {

}
