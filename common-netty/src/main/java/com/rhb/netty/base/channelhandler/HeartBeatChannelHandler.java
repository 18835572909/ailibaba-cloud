package com.rhb.netty.base.channelhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 心跳检测机制
 *  1. Server端注册IdeaStateHandler()，声明 读、写 超时范围
 *  2. 重写handler的 userEventTriggered()，对事件进行监听
 *
 * @author renhuibo
 * @date 2021/9/26 17:00
 */
public class HeartBeatChannelHandler extends SimpleChannelHandler<String> {

  int readIdleTimes = 0;

  @Override
  public void channelRead(Channel channel, String s) {
    System.out.println(" ====== > [server] message received : " + s);
    if("I am alive".equals(s)){
      channel.writeAndFlush("copy that");
    }else {
      System.out.println(" 其他信息处理 ... ");
    }
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    IdleStateEvent event = (IdleStateEvent)evt;

    String eventType = null;
    switch (event.state()){
      case READER_IDLE:
        eventType = "读空闲";
        readIdleTimes ++; // 读空闲的计数加1
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
    if(readIdleTimes > 3){
      System.out.println(" [server]读空闲超过3次，关闭连接");
      ctx.channel().writeAndFlush("you are out");
      ctx.channel().close();
    }
  }
}
