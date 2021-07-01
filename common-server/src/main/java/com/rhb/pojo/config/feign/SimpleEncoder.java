package com.rhb.pojo.config.feign;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义Feign的编码器
 *
 * @author renhuibo
 * @date 2021/7/1 16:17
 */
@Slf4j
public class SimpleEncoder implements Encoder {

  @Override
  public void encode(Object object, Type bodyType, RequestTemplate requestTemplate) throws EncodeException {
    log.info("encode object is class"+object.getClass().getName());
    log.info("encode object is value"+object);
    log.info("encode bodyType is class"+bodyType.getClass().getName());
    log.info("encode bodyType is value"+bodyType);
    new Encoder.Default().encode(object, bodyType, requestTemplate);
  }
}
