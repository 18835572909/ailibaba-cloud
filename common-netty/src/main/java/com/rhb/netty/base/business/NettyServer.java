package com.rhb.netty.base.business;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/23 10:23
 */
public class NettyServer implements Runnable{

  private static final Logger log = LoggerFactory.getLogger(NettyServer.class);

  private Integer port;

  public NettyServer(Integer port){
    this.port = port;
  }

  @Override
  public void run() {
    start();
  }

  public void start(){
    EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    ChannelFuture channelFuture = null;
    try{
      serverBootstrap.group(bossEventLoopGroup,workerEventLoopGroup)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG,128)
          .handler(new IdleStateHandler(2,2,1,TimeUnit.SECONDS))
          .childHandler(new MyChannelInitializer());

      channelFuture = serverBootstrap
          .bind(new InetSocketAddress(port)).syncUninterruptibly();

//      channelFuture.channel().closeFuture().syncUninterruptibly();
    }catch (Exception e){
      // 具体处理
      e.printStackTrace();
    }finally {
//      bossEventLoopGroup.shutdownGracefully();
//      workerEventLoopGroup.shutdownGracefully();
      if(null != channelFuture && channelFuture.isSuccess()){
        log.info("Netty Server Start OK! port:{}",Constant.SERVER_PORT);
      }else{
        log.info("Netty Server Start Fail!");
      }
    }
  }

  public static void main(String[] args) {
    int cpuCount = Runtime.getRuntime().availableProcessors();
    int threadCount = cpuCount * 2 + 1;
    log.info("CPU Count: {}",cpuCount);

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadCount, threadCount, 0,
        TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
        new ThreadFactoryBuilder().setNameFormat("Netty Server - %d").build());

    threadPoolExecutor.submit(new NettyServer(Constant.SERVER_PORT));
  }

}
