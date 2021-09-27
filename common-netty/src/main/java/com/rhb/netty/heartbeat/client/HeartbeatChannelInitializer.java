package com.rhb.netty.heartbeat.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 16:54
 */
public class HeartbeatChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    socketChannel.pipeline()
        .addLast(new StringEncoder())
        .addLast(new StringDecoder())
        .addLast(new IdleStateHandler(3,3,5))
        .addLast(new HeartbeatChannelHandler());

  }
}
