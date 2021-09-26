package com.rhb.netty.base.channelhandler;

import cn.hutool.json.JSONUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

/**
 * Netty Server 充当 HttpServer
 *
 * @author renhuibo
 * @date 2021/9/24 18:22
 */
@Slf4j
public class HttpChannelHandler extends SimpleChannelHandler<FullHttpRequest> {

  /**
   * http的编码器/解码器
   *  HttpRequestDecoder 即把 ByteBuf 解码到 HttpRequest 和 HttpContent。
   *  HttpResponseEncoder 即把 HttpResponse 或 HttpContent 编码到 ByteBuf。
   *  HttpServerCodec 即 HttpRequestDecoder 和 HttpResponseEncoder 的结合。
   */
  @Override
  public void channelRead(Channel channel, FullHttpRequest fullHttpRequest) {
    HttpMethod method = fullHttpRequest.method();

    if(HttpMethod.GET.equals(method)){
      log.info("param:{}", JSONUtil.parseObj(CommonUtil.getParams(fullHttpRequest)));
      CommonUtil.send(channel,"GET请求收到");
    }
    if(HttpMethod.POST.equals(method)){
      log.info("body:{}",CommonUtil.getBody(fullHttpRequest));
      CommonUtil.send(channel,"POST请求收到");
    }
  }



}
