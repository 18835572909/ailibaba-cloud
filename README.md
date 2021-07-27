# ailibaba-cloud
springcloudalibaba体系组件使用

1. redis
    - cache
    - lock
      - RedLock：使用多个互不关联的节点数据（n/2+1个节点反馈获取锁，才算成功）
                  解决在获取master过程中获取锁，但是slave没用同步数据的时候，master挂掉，slave晋升master的现象
      - 乐观锁： version
      - 悲观锁： for update
      - 分布式锁：
        - Zookeeper
        - Ression
        - Lua脚本: if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end
        - redis.setnex()
    - 三大问题
       - 雪崩：由于缓存时间一直，在某个时刻，多数的缓存数据集体失效（添加抖动时间）
       - 击穿：热点数据在高峰挂掉，段时间内对db进行一个具体的冲击（预热）
       - 穿透：对DB和Redis中都没有的数据，高峰期大规模请求（布隆过滤器：将大部分存储数据通过布隆过滤器散列在一个大的bitmap中）
2. queue
    - RocketMQ
    - RabbitMQ
    - Kfaka（没用过）
3. security
    - spring security（没用过）
    - shiro
    - jwt
4. 动态代理
    - JDK - InvocationHandler + Proxy.newInstance()
    - Cglib - MethodHandler + Enhancer
5. spring cloud
    - ribbon
    - feign
    - zool
    - gateway
    - sentinel
    - seata
6. spring boot && Spring
    - 全局异常处理：@ControllerAdvice
    - 自定义标签：AOP+@Annotation+JDK reflect
    - ApplicationRunner + @Order
    - 各种标签的使用：@PostContruct ...
    - 特定场合框架
        - Spring StateMachine
        - STOMP + WebSocket
        - Netty （不熟）
        - Spring WebFlux （没用过）
        - Spring Batch（没用过）
    - 常见序列化方式: 以某种先前定义好的方式对数据进行压缩使得P对P可以简洁小流量的方式完成数据传输
        - JdkSerializers(ByteArray)
        - FastJSON等等的JSON
        - RPC ：protobuf
7. 调度框架：
    - 分布式
      - Quartz
      - xxljob
    - 单点使用
      - @Schedule: Spring框架自带
      - Timer
      - Executors.newSchedule(): JUC
8. 分库分表组件：
    - ShardingJDBC
        - 配置分库分表策略
        - 数据脱敏配置         
9. 自定义spring-boot-starter
    - 源码解析auto装载过程
    - META-INF文件文件用途
    - 自定义starter
    - spi使用原理和样例
