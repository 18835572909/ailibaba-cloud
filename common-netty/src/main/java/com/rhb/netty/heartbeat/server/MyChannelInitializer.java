package com.rhb.netty.heartbeat.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 16:36
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel sc) throws Exception {
    sc.pipeline()
        // 解决TCP沾包、拆包的问题
        .addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Unpooled.copiedBuffer("$_".getBytes())))
        .addLast(new StringEncoder())
        .addLast(new StringDecoder())
        .addLast(new HeartbeatChannelHandler());

  }
}
