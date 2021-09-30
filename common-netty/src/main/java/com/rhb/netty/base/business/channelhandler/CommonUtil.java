package com.rhb.netty.base.business.channelhandler;

import cn.hutool.core.util.CharsetUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.util.HashMap;
import java.util.Map;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/26 17:04
 */
public class CommonUtil {

  static void send(Channel channel,String context){
    DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
        HttpResponseStatus.OK, Unpooled.copiedBuffer(context, CharsetUtil.CHARSET_UTF_8));
    httpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE,"application/json");
    // 特别注意： 一定要有添加监听器去关闭Channel
    channel.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
  }

  static String getBody(FullHttpRequest fullHttpRequest){
    ByteBuf content = fullHttpRequest.content();
    return content.toString(CharsetUtil.CHARSET_UTF_8);
  }

  static Map<String,Object> getParams(FullHttpRequest fullHttpRequest){
    String uri = fullHttpRequest.uri();
    String[] split = uri.split("\\?");
    if(split.length>1){
      Map<String,Object> params = new HashMap<>();
      String paramStr = split[1];
      String[] paramMap = paramStr.split("&");
      for(String param:paramMap){
        String[] keyValue = param.split("=");
        params.put(keyValue[0],keyValue[1]);
      }
      if(!params.isEmpty()){
        return params;
      }
    }
    return null;
  }

}
