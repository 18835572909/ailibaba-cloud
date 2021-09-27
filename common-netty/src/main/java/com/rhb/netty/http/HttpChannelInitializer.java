package com.rhb.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 *
 *
 * @author renhuibo
 * @date 2021/9/27 15:57
 */
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel sc) throws Exception {
    sc.pipeline()
        .addLast(new HttpServerCodec())
        .addLast(new HttpObjectAggregator(512*1024))
        .addLast(new MyHttpChannelHandler());
  }
}
