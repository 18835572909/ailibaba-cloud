package com.rhb.netty.io;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/30 16:51
 */
public class FileClient {

  private static final String CR = System.getProperty("line.separator");

  public static void main(String[] args) {
    Bootstrap bootstrap = new Bootstrap();
    NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    try{
      bootstrap.group(eventLoopGroup)
          .channel(NioSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG,128)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {

            }
          });

      ChannelFuture channelFuture = bootstrap.connect("localhost", 8023).sync();
      if(channelFuture.channel().isActive()){
          System.out.println("Netty Client Connection OK");
      }
      channelFuture.channel().writeAndFlush(""+CR);

      channelFuture.channel().close().sync();
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      eventLoopGroup.shutdownGracefully();
    }
  }

}
