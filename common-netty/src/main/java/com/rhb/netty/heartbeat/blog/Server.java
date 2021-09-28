package com.rhb.netty.heartbeat.blog;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

/**
 * 这里的数据包：
 * content-length = 4(content-length) + 1(command )+ content-length
 * 数据帧的长度 = 所有数据包的长度
 *
 * @author renhuibo
 * @date 2021/9/28 11:50
 */
public class Server {
  public static void main(String[] args) {
    NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    NioEventLoopGroup workGroup = new NioEventLoopGroup(4);
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap
          .group(bossGroup, workGroup)
          .channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
              ChannelPipeline p = socketChannel.pipeline();
              p.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
              p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0));
              p.addLast(new ServerHandler("NettyServer"));
            }
          });

      Channel ch = bootstrap.bind(12345).sync().channel();
      ch.closeFuture().sync();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      bossGroup.shutdownGracefully();
      workGroup.shutdownGracefully();
    }
  }
}
