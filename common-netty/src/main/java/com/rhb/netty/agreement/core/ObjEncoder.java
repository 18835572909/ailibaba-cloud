package com.rhb.netty.agreement.core;

import com.rhb.netty.util.SearializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义协议编码器
 *
 *  数据长度 + 指令 + 数据
 *
 * @author renhuibo
 * @date 2021/9/27 11:46
 */
public class ObjEncoder extends MessageToByteEncoder<AgreementPacket> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext,
      AgreementPacket agreementPacket, ByteBuf byteBuf) throws Exception {
    byte[] serialize = SearializationUtil.serialize(agreementPacket);

    byteBuf.writeInt(serialize.length+1);
    byteBuf.writeByte(agreementPacket.getCommand());
    byteBuf.writeBytes(serialize);
  }

}
