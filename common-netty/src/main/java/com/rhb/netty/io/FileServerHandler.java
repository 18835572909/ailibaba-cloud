package com.rhb.netty.io;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.DefaultFileRegion;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedFile;
import java.io.File;
import java.io.RandomAccessFile;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/30 16:20
 */
public class FileServerHandler extends ChannelInboundHandlerAdapter {

  private static final String CR = System.getProperty("line.separator");

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    String path = msg.toString();
    File file = new File(path);
    if(file.exists()){
      RandomAccessFile raf = null;
      long length = -1;
      try{
        raf = new RandomAccessFile(file,"r");
        length = raf.length();
      }catch (Exception e){
        ctx.writeAndFlush("ERR:"+e.getClass().getSimpleName()+":"+e.getMessage());
        return;
      }finally {
        if(length<0 && raf!=null){
          raf.close();
        }
      }

      ctx.write(file+""+file.length()+CR);

      // SSL不支持零拷贝
      if(ctx.pipeline().get(SslHandler.class)==null){
        ctx.write(new DefaultFileRegion(raf.getChannel(),0,length));
      }else{
        ctx.write(new ChunkedFile(raf));
      }
      ctx.writeAndFlush(CR);
    }else{
      ctx.writeAndFlush("ERR:文件不存在"+CR);
      return;
    }

  }
}
