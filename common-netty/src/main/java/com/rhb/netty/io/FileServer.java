package com.rhb.netty.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.CharsetUtil;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLException;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/30 16:28
 */
public class FileServer {

  static final boolean SSL = System.getProperty("ssl")!=null;

  static final int PORT = Integer.parseInt(System.getProperty("port",SSL?"8992":"8023"));

  public static void main(String[] args) throws CertificateException, SSLException {
    final SslContext sslCtx;

    if(SSL){
      SelfSignedCertificate ssc = new SelfSignedCertificate();
      sslCtx = SslContext.newServerContext(ssc.certificate(),ssc.privateKey());
    }else{
      sslCtx = null;
    }

    EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    try{
      serverBootstrap.group(bossEventLoopGroup,workerEventLoopGroup)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG,128)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
              ChannelPipeline pipeline = socketChannel.pipeline();
              // SSL支持
              if (sslCtx != null) {
                pipeline.addLast(sslCtx.newHandler(socketChannel.alloc()));
              }

              pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
              // 限制最大 8M
              pipeline.addLast(new LineBasedFrameDecoder(1024*8));
              pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
              pipeline.addLast(new FileServerHandler());
            }
          });

      ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();

      if(channelFuture!=null && channelFuture.isSuccess()){
        System.out.println("Netty Server Start OK");
      }

      channelFuture.channel().closeFuture().sync();
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      bossEventLoopGroup.shutdownGracefully();
      workerEventLoopGroup.shutdownGracefully();
    }
  }

}
