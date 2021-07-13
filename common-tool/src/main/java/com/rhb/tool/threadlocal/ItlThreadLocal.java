package com.rhb.tool.threadlocal;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

/**
 *
 *
 * @author renhuibo
 * @date 2021/7/12 15:04
 */
public class ItlThreadLocal {

  private static ThreadLocal<String> itl = new InheritableThreadLocal<>();

  private static ThreadLocal<String> tl = new ThreadLocal<>();

  private static ThreadLocal<String> ttl = new TransmittableThreadLocal<>();

  private final static ThreadPoolExecutor T_P_E = new ThreadPoolExecutor(1,1,0, TimeUnit.SECONDS,new LinkedBlockingDeque<>(),new ThreadFactoryBuilder().setNameFormat("itl-%d").build());

  /**
   * InheritableThreadLocal:
   *  1. 解决子线程中获取不到父线程本地变量问题
   *  2. 子线程可以获取到父线程的本地变量
   */
  public static void itl(){

    itl.set("hello wolrd");

    sleep(1000);

    new Thread(){
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()+":"+itl.get());
      }
    }.start();

    System.out.println(Thread.currentThread().getName()+":"+itl.get());
  }

  /**
   * 展示：ThreadPool所有容量初始后，再set值，子线程获取不到父线程本地变量的问题
   * 1. 使用线程池后，在没有初始化之前set值，itl是可以获取到本地变量的
   * 2. 使用线程池，并且在初始化之后set值，itl中获取不到初始化之后set的本地变量值
   */
  public static void itl_tp(){
    itl.set("hello world");
    T_P_E.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()+":"+itl.get());
      }
    });

    itl.set("hello world2");
    T_P_E.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()+":"+itl.get());
      }
    });

  }

  /**
   * ThreadLocal的基本使用：解决线程中变量传递和隔离问题
   * 1. 同一线程内取到set的本地变量是相同，异步处理或者其他线程中获取不到非当前线程set的本地变量
   */
  public static void tl(){
    tl.set("hello wolrd");

    System.out.println(Thread.currentThread().getName()+":"+tl.get());

    sleep(1000);

    new Thread(){
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()+":"+tl.get());
      }
    }.start();

    System.out.println(Thread.currentThread().getName()+":"+tl.get());
  }

  public static void itl_fail(){

    itl.set("hello"); // 等上面的线程池第一次启用完了，父线程再给自己赋值
    T_P_E.execute(()->{
      System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), itl.get()));
    });

    itl.set("hello2"); // 等上面的线程池第一次启用完了，父线程再给自己赋值
    sleep(300);

    T_P_E.execute(()->{
      System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), itl.get()));
    });

    System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), itl.get()));
  }

  public static void ttl_ok(){
    ttl.set("hello"); // 等上面的线程池第一次启用完了，父线程再给自己赋值
    T_P_E.execute(()->{
      System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), ttl.get()));
    });

    ttl.set("hello2"); // 等上面的线程池第一次启用完了，父线程再给自己赋值
    sleep(300);
    T_P_E.execute(()->{
      System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), ttl.get()));
    });

    System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), ttl.get()));
  }

  public static void sleep(int sleep){
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void shutdown(){
    if(!T_P_E.isShutdown()){
      T_P_E.shutdown();
    }
  }

  public static void main(String[] args) {
//    itl();
//    itl_tp();
//    tl();
//    tl_main();
    itl_fail();
    ttl_ok();
    shutdown();
  }

}
