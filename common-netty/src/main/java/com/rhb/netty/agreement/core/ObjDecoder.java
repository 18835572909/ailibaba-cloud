package com.rhb.netty.agreement.core;

import com.rhb.netty.util.SearializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 11:51
 */
@Slf4j
public class ObjDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf,
      List<Object> list) throws Exception {

    byteBuf.markReaderIndex();
    int dataLength = byteBuf.readInt();
    if(byteBuf.readableBytes() < dataLength){
      log.info("数据包长度与申明长度不同...");
      byteBuf.resetReaderIndex();
      return;
    }

    byte command = byteBuf.readByte();
    byte[] buf = new byte[dataLength-1];
    byteBuf.readBytes(buf);
    AgreementPacket packet = SearializationUtil.deserialize(buf, AgreementPacket.get(command));
    list.add(packet);
  }
}
