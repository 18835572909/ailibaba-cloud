模块主要组成

1. rpc协议之ProtoBuf （主要是：序列化方式）
- message 消息体定义
- option 参数设置
- int32\string... 数据类型
- 常用关键字 
- 思考： protobuf会不会在后期持续修改后，对之前的业务产生很大的影响，
  是不是.proto文件最大的难点在于: 更新、迭代、复用
> 使用protobuf，首先安装编译器，idea整合编译器，使用插件GenProtoBuf.
  在Tool->Configure GenProtoBuf中配置编译器的运行目录，以及指定，文件
  生成后“相对路径的根目录”，指定编译的方法：安装插件后，右键.proto
  文件，选择quick gen protobuf rules,生成java文件。

2. rpc协议之Thrift
- 序列化机制、传输层、并发框架
- 类似：WebService(可以实现跨语言传输)【刨除序列化原理的话，有点像socket(通讯)+sdk(序列化)】
- 学习博客：
  > Thrift入门：https://blog.csdn.net/dnc8371/article/details/106707361
  > protobuf与Thrift区别：https://www.coonote.com/cplusplus-note/protobuf-thrift-diff.html