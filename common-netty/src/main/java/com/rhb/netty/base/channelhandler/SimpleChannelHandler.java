package com.rhb.netty.base.channelhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ChannelInboudHandler自定义父类
 *
 * @author renhuibo
 * @date 2021/9/24 17:35
 */
public abstract class SimpleChannelHandler<T> extends SimpleChannelInboundHandler<T> {

  private final Logger log = LoggerFactory.getLogger(SimpleChannelHandler.class);

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      T t) throws Exception {
    channelRead(channelHandlerContext.channel(),t);
  }

  public abstract void channelRead(Channel channel,T t);

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    super.channelRegistered(ctx);
    log.info("channelRegistered- 当 Channel 已经注册到它的 EventLoop 并且能够处理 I/O 时被调用");
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    super.channelUnregistered(ctx);
    log.info("channelUnregistered- 当Channel 已经在EventLoop中注销 并且不能处理I/O时调用");
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    super.channelReadComplete(ctx);
    log.info("channelReadComplete- 当Channel 的上一个读操作结束时调用");
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    super.userEventTriggered(ctx, evt);
  }

  @Override
  public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    super.channelWritabilityChanged(ctx);
    log.info("channelWritabilityChanged- 当Channel 的可写状态发生改变时调用，避免写入太快造成OutofMemoryError");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    log.info("channelActive- 当Channel 已经链接调用, 【{}】 链接",ctx.channel().remoteAddress());
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    log.info("channelInactive- 当Channel 断开链接时调用, 【{}】 链接",ctx.channel().remoteAddress());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    super.exceptionCaught(ctx, cause);
    log.info("exceptionCaught- 发生异常时调用");
  }
}
