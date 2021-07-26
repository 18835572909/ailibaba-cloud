package com.rhb.tool.aop.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
  String model() default "";
  String action() default "";
  @AliasFor(attribute = "value")
  String desc() default "";
  @AliasFor(attribute = "desc")
  String value() default "";
}
