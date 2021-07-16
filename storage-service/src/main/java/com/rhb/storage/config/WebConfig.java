package com.rhb.storage.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.rhb.storage.serialize.DefineArgumentResolver;
import com.rhb.storage.serialize.LongSerializer;
import java.math.BigInteger;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author renhuibo
 * @date 2021/7/16 10:31
 */
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     *  将Long,BigInteger序列化的时候,转化为String
     */
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(Long.class, LongSerializer.INSTANCE);
    simpleModule.addSerializer(Long.TYPE, LongSerializer.INSTANCE);
    simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);

    objectMapper.registerModule(simpleModule);
    messageConverter.setObjectMapper(objectMapper);
    converters.add(0,messageConverter);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new DefineArgumentResolver());
  }
}
