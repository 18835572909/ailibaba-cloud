package com.rhb.pojo.config.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义Feign的ErrorDecoder
 *
 * @author renhuibo
 * @date 2021/7/1 16:16
 */
@Slf4j
public class SimpleErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String key, Response response) {
    switch (response.status()){
      case 400:
        log.info("服务400参数错误,返回:{}",response.body());
        break;
      case 404:
        log.info("服务404参数错误,返回:{}",response.body());
        break;
      default:
        return new Exception("Generic error");
    }
    return new ErrorDecoder.Default().decode(key, response);
  }

}
