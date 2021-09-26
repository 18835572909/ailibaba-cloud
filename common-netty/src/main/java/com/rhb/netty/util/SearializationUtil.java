package com.rhb.netty.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

/**
 * Objenesis: 小型Java库，主要用途：实例化一个特定类的新对象
 * 通俗讲：替代cla.newInstance();
 *
 * Protostuff:
 *
 *
 * @author renhuibo
 * @date 2021/9/23 14:02
 */
public class SearializationUtil {

  private static Map<Class<?>, Schema<?>> cacheSchema = new ConcurrentHashMap<>();

  private static Objenesis objenesis = new ObjenesisStd();

  private static <T> Schema<T> getSchema(Class<T> cla){
    Schema<T> schema = (Schema<T>)cacheSchema.get(cla);
    if(schema == null){
      schema = RuntimeSchema.createFrom(cla);
      cacheSchema.put(cla,schema);
    }
    return schema;
  }

  public static <T> byte[] serialize(T obj){
    Class<T> cla = (Class<T>)obj.getClass();
    LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    try{
      Schema<T> schema = getSchema(cla);
      return ProtostuffIOUtil.toByteArray(obj,schema,buffer);
    }catch(Exception e){
      throw new IllegalStateException(e.getMessage(),e);
    }finally {
      buffer.clear();
    }
  }

  public static <T> T deserialize(byte[] data,Class<T> cla){
    try{
      T message = objenesis.newInstance(cla);
      Schema<T> schema = getSchema(cla);
      ProtostuffIOUtil.mergeFrom(data,message,schema);
      return message;
    }catch (Exception e){
      throw new IllegalStateException(e.getMessage(),e);
    }
  }

}
