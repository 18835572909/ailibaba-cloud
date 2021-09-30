package com.rhb.netty.heartbeat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 16:56
 */
@Slf4j
public class HeartbeatChannelHandler extends SimpleChannelInboundHandler{

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o)
      throws Exception {
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    log.info("客户端-链接：{}",ctx.channel().remoteAddress());
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("客户端-断开：{}",ctx.channel().remoteAddress());
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if(evt instanceof IdleStateEvent){
      IdleStateEvent event = (IdleStateEvent) evt;

      if(IdleState.WRITER_IDLE.equals(event.state())){
        log.info("读取超时..");

        String heartbeat = "ping&_";
        ctx.channel().writeAndFlush(heartbeat);
      }

      if(IdleState.READER_IDLE.equals(event.state())){
        log.info("写入超时..");
      }

      if(IdleState.ALL_IDLE.equals(event.state())){
        log.info("读取、写入超时..");
      }
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
  }
}
