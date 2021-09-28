package com.rhb.netty.heartbeat.blog;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import java.net.SocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/28 10:50
 */
@Slf4j
public abstract class CustomHeartbeatHandler extends SimpleChannelInboundHandler<ByteBuf> {

  public static final byte PING_MSG = 1;
  public static final byte PONG_MSG = 2;
  public static final byte CUSTOM_MSG = 3;

  protected String name;

  private int heartbeatcount = 0;

  public CustomHeartbeatHandler(String name) {
    this.name = name;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext chc, ByteBuf byteBuf)
      throws Exception {
    /**
     * 为什么取index=4的位置？
     *
     * 基于报文格式，int占4个byte位，index=4的地方，存放的是byte类型的指令
     */
    if(byteBuf.readableBytes()<4){
      return;
    }

    if (byteBuf.getByte(4) == PING_MSG) {
      sendPongMsg(chc);
    } else if (byteBuf.getByte(4) == PONG_MSG){
      System.out.println(name + " get pong msg from " + chc.channel().remoteAddress());
    } else {
      handleData(chc, byteBuf);
    }
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent e = (IdleStateEvent) evt;
      switch (e.state()) {
        case READER_IDLE:
          handleReaderIdle(ctx);
          break;
        case WRITER_IDLE:
          handleWriterIdle(ctx);
          break;
        case ALL_IDLE:
          handleAllIdle(ctx);
          break;
        default:
          break;
      }
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    SocketAddress socketAddress = ctx.channel().remoteAddress();
    log.info("{} disconnect",socketAddress);
  }

  /**
   * 心跳检测，数据包只需要包含：长度+指令
   */
  protected void sendPingMsg(ChannelHandlerContext chc){
    ByteBuf buf = chc.alloc().buffer(5);
    buf.writeInt(5);
    buf.writeByte(PING_MSG);
    chc.writeAndFlush(buf);
    heartbeatcount++;
    System.out.println(name + " sent ping msg to " + chc.channel().remoteAddress() + ", count: " + heartbeatcount);
  }

  protected void sendPongMsg(ChannelHandlerContext chc){
    ByteBuf buf = chc.alloc().buffer(5);
    buf.writeInt(5);
    buf.writeByte(PONG_MSG);
    chc.writeAndFlush(buf);
    heartbeatcount++;
    System.out.println(name + " sent pong msg to " + chc.channel().remoteAddress() + ", count: " + heartbeatcount);
  }

  protected abstract void handleData(ChannelHandlerContext chc,ByteBuf buf);

  protected void handleReaderIdle(ChannelHandlerContext ctx) {
    System.err.println("---READER_IDLE---");
  }

  protected void handleWriterIdle(ChannelHandlerContext ctx) {
    System.err.println("---WRITER_IDLE---");
  }

  protected void handleAllIdle(ChannelHandlerContext ctx) {
    System.err.println("---ALL_IDLE---");
  }

  public static void sendInfo(ChannelHandlerContext ctx,String content){
    ByteBuf buf = ctx.alloc().buffer();
    buf.writeInt(5 + content.getBytes().length);
    buf.writeByte(CustomHeartbeatHandler.CUSTOM_MSG);
    buf.writeBytes(content.getBytes());
    ctx.writeAndFlush(buf);
  }

  public static void sendInfo(Channel ctx,String content){
    ByteBuf buf = ctx.alloc().buffer();
    buf.writeInt(5 + content.getBytes().length);
    buf.writeByte(CustomHeartbeatHandler.CUSTOM_MSG);
    buf.writeBytes(content.getBytes());
    ctx.writeAndFlush(buf);
  }
}
