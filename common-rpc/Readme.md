模块主要组成

1. rpc协议之ProtoBuf
- message 消息体定义
- option 参数设置
- int32\string... 数据类型
- 常用关键字 

> 使用protobuf，首先安装编译器，idea整合编译器，使用插件GenProtoBuf.
  在Tool->Configure GenProtoBuf中配置编译器的运行目录，以及指定，文件
  生成后“相对路径的根目录”，指定编译的方法：安装插件后，右键.proto
  文件，选择quick gen protobuf rules,生成java文件。

2. rpc协议之Thrift