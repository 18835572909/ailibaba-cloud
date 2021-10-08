package com.rhb.es.util;

import cn.hutool.core.util.CharsetUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/10/8 13:53
 */
public class ExceptionUtil {

  private static final int MAX_EXCEPTION_LENGTH = 256;

  public static String printExceptionMsg(Exception e){
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    e.printStackTrace(printStream);
    String result = new String(outputStream.toByteArray());
    printStream.close();
    try {
      outputStream.close();
    } catch (IOException e1) {
      result = e1.getMessage();
    }
    return result.substring(0,MAX_EXCEPTION_LENGTH);
  }

}
