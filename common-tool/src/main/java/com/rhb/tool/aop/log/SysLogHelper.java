package com.rhb.tool.aop.log;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 日志工具类
 *
 * @author renhuibo
 * @date 2021/7/26 10:35
 */
@Slf4j
public class SysLogHelper {

  public static final ThreadPoolExecutor LOG_TP = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
      new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setNameFormat("odmlog-%d").build());

  /**
   * 异步存储日志
   *
   * @param logger 日志
   */
  public static void saveLog(Logger logger) {
    log.info("系统日志| 模块：{}，动作：{}，描述：{}，操作人：{}", logger.getModel(), logger.getAction(),
        logger.getDesc(), logger.getOperator());
    LOG_TP.submit(() -> {
      // TODO 持久化日志记录
    });
  }

  /**
   * 存储简化日志：类名+方法名+描述
   *
   * @param desc 动作描述（备注）
   */
  public static void saveSimpleLogger(String desc) {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    String className = stackTrace[2].getClassName();
    if(!StringUtils.isEmpty(className)){
      String[] split = className.split("\\.");
      className = split[split.length-1];
    }
    String methodName = stackTrace[2].getMethodName();
    Logger logger = Logger.builder()
        .optime(new Date())
        .action(methodName)
        .model(className)
        .desc(desc)
        // TODO 获取当前用户
        .operator("")
        .build();
    saveLog(logger);
  }
}
