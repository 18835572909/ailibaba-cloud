package com.rhb.netty.http;

import cn.hutool.json.JSONUtil;
import com.google.common.net.HttpHeaders;
import com.rhb.netty.base.channelhandler.SimpleChannelHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 16:02
 */
@Slf4j
public class MyHttpChannelHandler extends SimpleChannelHandler<FullHttpRequest> {

  @Override
  public void channelRead(Channel channel, FullHttpRequest fullHttpRequest) {
    HttpMethod method = fullHttpRequest.method();

    if(HttpMethod.GET.equals(method)){
      log.info("GET : {}", JSONUtil.parseObj(getParams(fullHttpRequest)));
    }
    if(HttpMethod.POST.equals(method)){
      log.info("POST : {}",JSONUtil.parseObj(getBody(fullHttpRequest)));
    }

    sendMsg(channel,"消息已收到");
  }

  private void sendMsg(Channel channel,String context){
    DefaultFullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
        HttpResponseStatus.OK, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
    fullHttpResponse.headers().set(HttpHeaders.CONTENT_TYPE,"application/json");
    channel.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
  }

  public String getBody(FullHttpRequest request){
   return request.content().toString(CharsetUtil.UTF_8);
  }

  public Map<String,Object> getParams(FullHttpRequest request){
    String uri = request.uri();
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
