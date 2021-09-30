package com.jdk.resource.thread;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/30 11:47
 */
public class ThreadDemo {

  public static void main(String[] args) {
    final Object lock = new Object();

    Thread thread1 = new Thread(() -> {
      try {
        synchronized (lock) {
          System.out.println("t1 acquire lock");
          Thread.sleep(3000);
          System.out.println("t1 release lock");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    Thread thread2 = new Thread(() -> {
      try {
        synchronized (lock) {
          System.out.println("t2 acquire lock");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    try{
      thread1.start();
      Thread.sleep(1000);
      thread1.stop();
      thread2.start();
    }catch (Exception e){
      e.printStackTrace();
    }
  }

}
