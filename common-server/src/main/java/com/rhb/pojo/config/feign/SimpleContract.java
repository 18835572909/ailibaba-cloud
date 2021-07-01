package com.rhb.pojo.config.feign;

import feign.Contract;
import feign.MethodMetadata;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/1 16:17
 */
@Slf4j
public class SimpleContract implements Contract {

  @Override
  public List<MethodMetadata> parseAndValidateMetadata(Class<?> aClass) {
    log.info("contract:{}",aClass.getName());
    return new Contract.Default().parseAndValidateMetadata(aClass);
  }
}
