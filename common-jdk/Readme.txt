- 终极目标：JDK源码手工编写 

多线程系列
1. Thread.stop()[ThreadDeath]
  > 破坏原子性
  > 释放所有锁
  
2. Thread.interrupt():调用interrupt方法是在当前线程中打了一个停止标志，并不是真的停止线程。

3. Thread.interrupted():测试当前线程是否已经中断。线程的中断状态由该方法清除。

4. Thread.isInterrupted() :测试当前线程是否已经中断。

5. 停止线程方法
  > 标记终端，及while(flag){} -> flag=false;
  > isInterrupted()+return
  > isInterrupted()+throw new InterruptedException()
  