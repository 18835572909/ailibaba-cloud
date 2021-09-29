package com.rhb.netty.heartbeat.blog;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import java.nio.channels.SocketChannel;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/29 17:20
 */
public class SimpleHeartbeatHandler extends SimpleChannelInboundHandler<ByteBuf> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      ByteBuf byteBuf) throws Exception {

  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if(evt instanceof IdleStateEvent){
      System.out.println("------------netty event 触发----------");

      IdleStateEvent event = (IdleStateEvent) evt;

      if(event.state().equals(IdleState.READER_IDLE)){
        System.out.println("----------读取超时--------");
      }
    }
  }
}
