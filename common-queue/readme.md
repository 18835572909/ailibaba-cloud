##队列模块目标
1. 常用中间件基础使用
2. 处理队列中常见问题
   - 如何确保消息正确投递
   - 如何避免消息重复消费
   - 如何处理消息积压
   - 延迟消费实现
      
### Kafka （出于好奇，先从此入手）
1. 相关概念
    - ISR(In-Sync Replicas)：副本同步队列（ISR由leader维护，follower从leader同
    步数据会有延迟，replica.lag.time.max.ms[延迟时间]、replica.lag.max.messages[延迟条数]，
    当超过两个条件中任一个时，将follower从ISR剔除放入OSR中，新进入的follower也会
    进入OSR。AR=ISR+OSR）
    - OSR(Out-Sync Replicas)：剔除队列
    - AR(Assigned Replicas)：所有副本
    - broke：kafka的服务器，用户存储信息【持久化信息】
    - topic：存放消息订阅消费的路由信息
    - partition：消息分区，一个topic下有多个分区
    - 消费者分组：同一分组名的消费者
    - offset：消息存储在kafka的broke上，查找消息的时候的偏移量即为offset 
     
2. 架构模型
    - 一个典型的kafka集群中包含若干个Producer，若干个Broker，若干个Consumer，
    以及一个zookeeper集群； kafka通过zookeeper管理集群配置，选举leader，以及在
    Consumer Group发生变化时进行Rebalance（负载均 衡）；Producer使用push模式将
    消息发布到Broker；Consumer使用pull模式从Broker中订阅并消费消息。

### RabbitMQ 


 