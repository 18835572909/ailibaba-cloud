package com.rhb.netty.heartbeat.blog;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/28 11:51
 */
public class ServerHandler extends CustomHeartbeatHandler{

  public ServerHandler(String name) {
    super(name);
  }

  @Override
  protected void handleData(ChannelHandlerContext chc, ByteBuf buf) {
    byte[] data = new byte[buf.readableBytes() - 5];
    buf.skipBytes(5);
    buf.readBytes(data);
    String content = new String(data);
    System.out.println(name + " get content: " + content);
    CustomHeartbeatHandler.sendInfo(chc,"已经收到（"+content+")");
  }

  @Override
  protected void handleReaderIdle(ChannelHandlerContext ctx) {
    super.handleReaderIdle(ctx);
    System.err.println("---client " + ctx.channel().remoteAddress().toString() + " reader timeout, close it---");
    ctx.close();
  }
}
