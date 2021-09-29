&& 请求报文默认设置 &&
  int            byte        byte[]
content-length content-type content
存储方案（两种）
公式： content-length = content.length() + 1;
公式： content-length = content.length() + 5

1. Netty的基础组件：
Channel、ChannelHandler、ChannelHandlerContext、ChannelInitializer、ChannelFauture、ChannelOption、
EventLoop、EventLoopGroup、ChannelPipeline、ServerBootStrap、BootStrap

2. 运行原理

3. 解码、编码器:

4. 应用之Http服务器：HttpRequestEncoder、HttpResponseDecoder

5. 应用之心跳检测机制： IdeaStateHandler() + useEventTriggered()
   1> 客户端监听ALL_IDLE事件，发送Ping，服务器检测是ping的请求，响应pong
   2> 服务端监听READER_IDLE事件，同上。
   注意：这里IdleEvent事件，是指连接空闲时间 事件触发。（所以当你只开启server端，server端的IdleStateHandler()是不会触发的）

6. 应用之高效序列化：ProtoBuf、ProtoStuff

7. 沾包、拆包问题：（Netty提供的协议解析器）
  > 基于分隔符：
    DelimiterBasedFrameDecoder: 指定特定的分隔符，来切割帧
    LineBasedFrameDecoder: 使用"\r\n"来切割帧（LineBasedFrameDecoder比DelimiterBaseFrameDecoder快）

  > 基于长度：
    FixedLengthFrameDecoder：FixedLengthFrameDecoder(int frameLength) 构造函数指定帧长，来切割帧，固定长度切割
    LengthFieldBasedFrameDecoder:
      （1） maxFrameLength - 发送的数据包最大长度；
      （2） lengthFieldOffset - 长度域偏移量，指的是长度域位于整个数据包字节数组中的下标；
      （3） lengthFieldLength - 长度域的自己的字节数长度。
      （4） lengthAdjustment – 长度域的偏移量矫正。 如果长度域的值，除了包含有效数据域的长度外，还包含了其他域（如长度域自身）长度，那么，就需要进行矫正。矫正的值为：包长 - 长度域的值 – 长度域偏移 – 长度域长。
      （5） initialBytesToStrip – 丢弃的起始字节数。丢弃处于有效数据前面的字节数量。比如前面有4个节点的长度域，则它的值为4。

| ---------------------------------------------------------------------------------------------------|
|    >> FixedLengthFrameDecoder主要处理 定长帧。对于变长的帧，需要使用LengthFieldBasedFrameDecoder。
|    >> blog包下的协议处理讲解：（我的理解：这个构造的每个值都不是固定的，要结合Decoder时，使用）
|       new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0)：
|       - 1024 是帧的最大长度
|       - 0    长度域下标
|       - 4    长度域字节数
|       - -4   长度域包含长度域的长度，这里要调整为 数据域的长度 ，所以是-4
|         包长 - 长度域的值 – 长度域偏移 – 长度域长 ==》X - X - 0 - 4 = -4
|       - 0  丢弃的起始字节数为0
|
| 这块的结合使用还是有点模糊，比如说initialBytesToStrip=0，或者等于5，具体影响到的是什么地方？
| --------------------------------------------------------------------------------------------------|

8. 重点注意：
  7里面，提供的协议解析器（按照分隔符、按照数据包长度） 等价于 我们自定义的ObjEncoder、ObjDecoder解析器。
  不同之处：
    1. 自定义的协议解析器，在实际Handler之前，已经将ByteBuf的数据转变成具体的对象，在Handler中接受的参数，
  就是自定义协议解析器，反序列后的对象。
    2. Netty提供的分隔符、数据包长度的两种解析器，在实际handler处理的时候，还是ByteBuf.

9. 断线重连：涉及两个部分
  核心方法是： EventLoop.schedule(new Runnable(),ttl,TimeUnit);

  1. 首次连接时，连接失败，重新连接：client.connect().addListener(new ChannelFutureListener());
  2. 连接成功后，中途断开，触发ChannelInactive()时，重新连接：ChannelHandlerContext.channel().eventLoop().schedule();