package com.rhb.netty.heartbeat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 16:29
 */
@Slf4j
public class HeartbeatNettyServer implements Runnable{

  @Override
  public void run() {
    NioEventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
    NioEventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    ChannelFuture channelFuture = null;
    try{
      serverBootstrap.group(bossEventLoopGroup,workerEventLoopGroup)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG,1024)
          .childOption(ChannelOption.SO_KEEPALIVE,true)
          .handler(new IdleStateHandler(5,5,10, TimeUnit.SECONDS))
          .childHandler(new HeartbeatChannelHandler());

      channelFuture = serverBootstrap.bind(10002).syncUninterruptibly();

    }catch (Exception e){
      log.error("socket server start error", e.getMessage());
    }finally {
      if (null != channelFuture && channelFuture.isSuccess()) {
        log.info("socket server start done. ");
      } else {
        log.error("socket server start error. ");
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new HeartbeatNettyServer()).start();
  }
}
