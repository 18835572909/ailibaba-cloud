package com.rhb.pojo.config.feign;

import cn.hutool.json.JSONUtil;
import feign.FeignException;
import feign.Response;
import feign.Response.Body;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义Decode的解码器
 *
 * @author renhuibo
 * @date 2021/7/1 16:17
 */
@Slf4j
public class SimpleDecoder implements Decoder {

  @Override
  public Object decode(Response response, Type type)
      throws IOException, DecodeException, FeignException {
    log.info("decode#type:{}",type.getTypeName());
    // body中数据只能读取一遍
    Body body = response.body();
    if (body == null) {
      return null;
    } else if (String.class.equals(type)) {
      return Util.toString(body.asReader(Util.UTF_8));
    } else {
      return JSONUtil.toBean(body.toString(), (Class) type);
    }
  }
}
