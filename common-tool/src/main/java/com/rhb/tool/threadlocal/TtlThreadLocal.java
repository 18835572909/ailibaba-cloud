package com.rhb.tool.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 带有失效时间的ThreadLocal
 *
 * @author renhuibo
 * @date 2021/7/12 11:35
 */
public class TtlThreadLocal {

  private final static ThreadPoolExecutor T_P_E =
      new ThreadPoolExecutor(1,1,0, TimeUnit.SECONDS,new LinkedBlockingDeque<>(),new ThreadFactoryBuilder().setNameFormat("ttl_threadlocal-%d").build());

  private static ThreadLocal<String> ttl = new TransmittableThreadLocal<>();

  public static void ttl() throws InterruptedException {
    T_P_E.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()+":"+ttl.get());
      }
    });

    T_P_E.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()+":"+ttl.get());
      }
    });

    Thread.sleep(1000);
    ttl.set("hello world");

    T_P_E.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()+":"+ttl.get());
      }
    });

    T_P_E.shutdown();
  }

  public static void itl_fail(){
    System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), ttl.get()));

    T_P_E.execute(()->{
      System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), ttl.get()));
    });

    ttl.set("hello"); // 等上面的线程池第一次启用完了，父线程再给自己赋值
    sleep(300);

    T_P_E.execute(()->{
      System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), ttl.get()));
    });

    System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), ttl.get()));
  }

  public static void sleep(int sleep){
    try {
      Thread.sleep(sleep);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public static void shutdown(){
    if(!T_P_E.isShutdown()){
      T_P_E.shutdown();
    }
  }

  public static void main(String[] args) throws InterruptedException {
//    ttl();
    itl_fail();
    shutdown();
  }


}
