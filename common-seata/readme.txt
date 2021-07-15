核心组成及概念：
TC (Transaction Coordinator) - 事务协调者
维护全局和分支事务的状态，驱动全局事务提交或回滚。
TM (Transaction Manager) - 事务管理器
定义全局事务的范围：开始全局事务、提交或回滚全局事务。
RM (Resource Manager) - 资源管理器
管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。


seata-server安装：

步骤一：启动包
点击下载
官方钉钉群（群号：23171167，1群5000人已满，2群, 3群: 32033786），qq群（群号: 254657148,2群: 216012363）群文件共享下载
步骤二：建表(仅db)
全局事务会话信息由3块内容构成，全局事务-->分支事务-->全局锁，对应表global_table、branch_table、lock_table

步骤三：修改store.mode
启动包: seata-->conf-->file.conf，修改store.mode="db或者redis"
源码: 根目录-->seata-server-->resources-->file.conf，修改store.mode="db或者redis"

步骤四：修改数据库连接|redis属性配置
启动包: seata-->conf-->file.conf，修改store.db或store.redis相关属性。
源码: 根目录-->seata-server-->resources-->file.conf，修改store.db或store.redis相关属性。

步骤五：启动
源码启动: 执行Server.java的main方法
命令启动: seata-server.sh -h 127.0.0.1 -p 8091 -m db -n 1 -e test
    -h: 注册到注册中心的ip
    -p: Server rpc 监听端口
    -m: 全局事务会话信息存储模式，file、db、redis，优先读取启动参数 (Seata-Server 1.3及以上版本支持redis)
    -n: Server node，多个Server时，需区分各自节点，用于生成不同区间的transactionId，以免冲突
    -e: 多环境配置参考 http://seata.io/en-us/docs/ops/multi-configuration-isolation.html