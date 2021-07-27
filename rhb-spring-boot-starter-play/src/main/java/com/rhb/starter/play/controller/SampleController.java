package com.rhb.starter.play.controller;

import com.rhb.starter.spi.SpiService;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/26 14:21
 */
@RestController
public class SampleController {

  @Autowired
  RedissonClient redissonClient;

  @GetMapping("test")
  public void starterTest(){
    RLock lock = redissonClient.getLock("redission_lock_1");
    try{
      if(lock.tryLock(1000, TimeUnit.SECONDS)){
        // TODO
      }
    }catch (Exception e){
      lock.unlock();
    }
    RLock fairLock = redissonClient.getFairLock("");
    RLock spinLock = redissonClient.getSpinLock("");
    RLock multiLock = redissonClient.getMultiLock(fairLock);
    RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("");
  }

  @GetMapping("spi")
  public void spiTest(){
    ServiceLoader<SpiService> loader = ServiceLoader.load(SpiService.class);
    Iterator<SpiService> iterator = loader.iterator();
    while(iterator.hasNext()){
      SpiService next = iterator.next();
      next.sayHello();
    }
  }

}
