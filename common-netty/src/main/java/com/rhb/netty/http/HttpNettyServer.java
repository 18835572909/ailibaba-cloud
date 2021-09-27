package com.rhb.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 15:54
 */
@Slf4j
public class HttpNettyServer implements Runnable{

  @Override
  public void run() {
    start();
  }

  public void start(){
    NioEventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
    NioEventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    Channel channel = null;
    ChannelFuture channelFuture = null;
    try{
      serverBootstrap.group(bossEventLoopGroup,workerEventLoopGroup)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG,128)
          .childHandler(new HttpChannelInitializer());

      channelFuture =
          serverBootstrap.bind(10001).syncUninterruptibly();

      channel = channelFuture.channel();
    }catch (Exception e){
      log.error("socket server start error", e.getMessage());
    }finally {
      if (null != channelFuture && channelFuture.isSuccess()) {
        log.info("socket server start done. ");
      } else {
        log.error("socket server start error. ");
      }
//      if(channel!=null){
//        channel.close();
//        bossEventLoopGroup.shutdownGracefully();
//        workerEventLoopGroup.shutdownGracefully();
//      }
    }
  }

  public static void main(String[] args) {
    new Thread(new HttpNettyServer()).start();
  }

}
