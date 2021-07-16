package com.rhb.storage.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/**
 * Long型序列化
 *
 * @author renhuibo
 * @date 2021/6/10 11:44
 */
public class LongSerializer extends JsonSerializer<Long> {

  public static final LongSerializer INSTANCE = new LongSerializer();

  @Override
  public void serialize(Long aLong, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    if(aLong>Integer.MAX_VALUE){
      jsonGenerator.writeString(aLong.toString());
    }else{
      jsonGenerator.writeNumber(aLong);
    }
  }


}
