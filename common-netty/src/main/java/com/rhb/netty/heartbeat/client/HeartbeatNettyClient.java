package com.rhb.netty.heartbeat.client;

import cn.hutool.core.date.DateUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 17:04
 */
@Slf4j
public class HeartbeatNettyClient implements Runnable{

  @Override
  public void run() {
    start();
  }

  public void start(){
    Bootstrap bootstrap = new Bootstrap();
    ChannelFuture channelFuture = null;
    try{
      bootstrap
          .group(new NioEventLoopGroup())
          .channel(NioSocketChannel.class)
          .handler(new HeartbeatChannelInitializer());

      channelFuture = bootstrap.connect("localhost", 10002);
//      channelFuture.addListener(new ChannelFutureListener() {
//        @Override
//        public void operationComplete(ChannelFuture channelFuture) throws Exception {
//          if(!channelFuture.isSuccess()){
//            EventLoop eventExecutors = channelFuture.channel().eventLoop();
//
//            eventExecutors.schedule(new Runnable() {
//              @Override
//              public void run() {
//                log.error("服务端链接不上，开始重连操作...");
//                System.err.println("服务端链接不上，开始重连操作...");
//                start();
//              }
//            },1, TimeUnit.SECONDS);
//          }else{
//            log.info("服务端链接成功...");
//            System.err.println("服务端链接成功...");
//
//          }
//        }
//      });
      sendMsg(channelFuture);
    }catch (Exception e){
      e.printStackTrace();
      log.error("socket server start error1", e.getMessage());
    }finally {
      if (null != channelFuture && channelFuture.isSuccess()) {
        log.info("socket server start done. ");
      } else {
        log.error("socket server start error2");
      }
    }
  }

  public void sendMsg(ChannelFuture channelFuture){
    Integer deply = new Random().nextInt(10);
    try{
      while(true){
        Thread.sleep(deply);
        channelFuture.channel().writeAndFlush("hello "+ DateUtil.now()+"$_");
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    new Thread(new HeartbeatNettyClient()).start();
  }

}
