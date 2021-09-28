package com.rhb.netty.heartbeat.blog;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/28 11:48
 */
public class ClientHandler extends CustomHeartbeatHandler {

  public ClientHandler(String name) {
    super(name);
  }

  @Override
  protected void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
    byte[] data = new byte[byteBuf.readableBytes() - 5];
    byteBuf.skipBytes(5);
    byteBuf.readBytes(data);
    String content = new String(data);
    System.out.println(name + " get content: " + content);
  }

  @Override
  protected void handleAllIdle(ChannelHandlerContext ctx) {
    super.handleAllIdle(ctx);
    sendPingMsg(ctx);
  }
}
