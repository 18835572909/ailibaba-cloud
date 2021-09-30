package com.rhb.netty.base.business;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.net.InetSocketAddress;
import java.util.Random;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/26 17:16
 */
public class NettyClient implements Runnable{

  @Override
  public void run() {
    NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(eventLoopGroup)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel sc) throws Exception {
            sc.pipeline()
                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new SimpleChannelInboundHandler<String>() {
                  @Override
                  protected void channelRead0(ChannelHandlerContext ctx, String msg)
                      throws Exception {
                    System.out.println(" client received :" +msg);
                    if(msg!= null && msg.equals("you are out")) {
                      System.out.println(" server closed connection , so client will close too");
                      ctx.channel().closeFuture();
                    }
                  }
                });

            sc.connect(new InetSocketAddress("localhost",Constant.SERVER_PORT)).syncUninterruptibly();
          }
        });
  }

  public void sendMsg(Channel channel,String text) throws Exception{
    int num = new Random().nextInt(10);
    Thread.sleep(num * 1000);
    channel.writeAndFlush(text);
  }

  public static void main(String[] args) {
    new Thread(new NettyClient()).start();
  }
}
