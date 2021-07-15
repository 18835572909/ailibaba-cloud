package com.rhb.pojo.config.feign;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义feign的重试策略
 *
 * @author renhuibo
 * @date 2021/7/1 16:17
 */
@Slf4j
public class SimpleRetryer implements Retryer {

  /**
   * 由于使用的Retryer.Defualt策略，这个方法将失效。
   * clone（）：返回的是Retryer.Default
   * @param e
   */
  @Override
  public void continueOrPropagate(RetryableException e) {
    log.info("重试策略：使用默认的策略："
        + "最大请求次数为5，"
        + "初始间隔时间为100ms，"
        + "下次间隔时间1.5倍递增，"
        + "重试间最大间隔时间为1s");
  }

  @Override
  public Retryer clone() {
    log.info("重试策略：clone()");
    /**
     * new Retryer.Default(this.period, this.maxPeriod, this.maxAttempts);
     * 也可以是使用这种方式定义重试次数
     */
    return new Retryer.Default().clone();
  }
}
