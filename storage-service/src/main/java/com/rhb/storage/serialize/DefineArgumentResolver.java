package com.rhb.storage.serialize;

import java.lang.reflect.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author renhuibo
 * @date 2021/7/16 10:42
 */
@Slf4j
public class DefineArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    Parameter parameter = methodParameter.getParameter();
    Class<?> type = parameter.getType();
    if(type.isAssignableFrom(Long.class)){
      log.info("param type long[supportsParameter] ...");
    }
    return true;
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter,
      ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
      WebDataBinderFactory webDataBinderFactory) throws Exception {
    Parameter parameter = methodParameter.getParameter();
    Class<?> type = parameter.getType();
    if(type.isAssignableFrom(Long.class)){
      log.info("param type long[resolveArgument] ...");
    }
    return null;
  }
}
