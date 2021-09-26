package com.rhb.netty.base;

import com.rhb.netty.base.channelhandler.HeartBeatChannelHandler;
import com.rhb.netty.base.channelhandler.HttpChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * ChannelHandler 安装到 ChannelPipeline 中的过程如下所示：
 * 一个ChannelInitializer的实现被注册到了ServerBootstrap中 ①；
 * 当 ChannelInitializer.initChannel()方法被调用时，ChannelInitializer
 * 将在 ChannelPipeline 中安装一组自定义的 ChannelHandler；
 * ChannelInitializer 将它自己从 ChannelPipeline 中移除。
 * 为了审查发送或者接收数据时将会发生什么，让我们来更加深入地研究 Ch
 *
 * @author renhuibo
 * @date 2021/9/23 10:30
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    socketChannel.pipeline()
        .addLast(new HttpRequestDecoder())
        .addLast(new HttpResponseEncoder())
        .addLast(new HttpObjectAggregator(512*1024))
        .addLast(new HttpChannelHandler())
        .addLast(new HeartBeatChannelHandler());
  }

}
