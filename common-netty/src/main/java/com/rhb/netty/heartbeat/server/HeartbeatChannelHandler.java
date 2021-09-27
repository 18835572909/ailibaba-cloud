package com.rhb.netty.heartbeat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 16:41
 */
@Slf4j
public class HeartbeatChannelHandler extends ChannelInboundHandlerAdapter {

  private AtomicInteger readIdleTimes;

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      log.info("msg：{}",msg);
      ctx.channel().writeAndFlush("收到");
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    IdleStateEvent event = (IdleStateEvent)evt;

    String eventType = null;
    switch (event.state()){
      case READER_IDLE:
        eventType = "读空闲";
        readIdleTimes.addAndGet(1); // 读空闲的计数加1
        break;
      case WRITER_IDLE:
        eventType = "写空闲";
        // 不处理
        break;
      case ALL_IDLE:
        eventType ="读写空闲";
        // 不处理
        break;
      default:
    }
    System.out.println(ctx.channel().remoteAddress() + "超时事件：" +eventType);
    if(readIdleTimes.get() > 3){
      System.out.println(" [server]读空闲超过3次，关闭连接");
      ctx.channel().writeAndFlush("you are out");
      ctx.channel().close();
    }
  }
}
