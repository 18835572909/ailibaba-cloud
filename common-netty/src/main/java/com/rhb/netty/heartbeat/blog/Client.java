package com.rhb.netty.heartbeat.blog;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/28 11:47
 */
public class Client {

  private Channel channel;

  private static Bootstrap bootstrap;

  public void connect(){
    NioEventLoopGroup workGroup = new NioEventLoopGroup(4);
    Random random = new Random(System.currentTimeMillis());
    try {
      bootstrap = new Bootstrap();
      bootstrap
          .group(workGroup)
          .channel(NioSocketChannel.class)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
              ChannelPipeline p = socketChannel.pipeline();
              /**
               * 客户端在读写5秒后，没有收到服务端读写操作，触发allIdleEvent
               */
              p.addLast(new IdleStateHandler(0, 0, 5, TimeUnit.SECONDS));
              p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0));
              p.addLast(new ClientHandler("NettyClient"));
            }
          });

      /**
       * 为什么要引出Channel?
       *
       * Channel是数据的载体，传递数据必须依靠Channel
       */
      doConnect(bootstrap);

      for (int i = 0; i < 3; i++) {
        int i1 = random.nextInt(20000);
        String content = "Main - client msg: " + i+"-"+i1;

        CustomHeartbeatHandler.sendInfo(channel,content);

        Thread.sleep(i1);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }finally {
      workGroup.shutdownGracefully();
    }
  }

  protected void doConnect(Bootstrap bootstrap) {
    if (channel != null && channel.isActive()) {
      return;
    }

    ChannelFuture future = bootstrap.connect("127.0.0.1", 12345);
    channel = future.channel();
    future.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture futureListener) throws Exception {
        if (futureListener.isSuccess()) {
          channel = futureListener.channel();
          System.out.println("-------------客户端连接成功----------------");
        } else {
          futureListener.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
              System.out.println("-------------客户端重新连接-----------------");
              doConnect(bootstrap);
            }
          }, 3, TimeUnit.SECONDS);
        }
      }
    });
  }

  public static void main(String[] args) {
    new Client().connect();
  }


}
