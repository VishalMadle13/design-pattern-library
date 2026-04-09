package dev.hashnode.systemdesigner.concurrency.monitor;

import lombok.Data;

@Data
//JavaBuiltInMonitor
public class Counter {
  private int count = 0;

  public synchronized void increment(){
    count++;
    System.out.println(Thread.currentThread().getName()+ " Java built-in monitor - count incremented:" + count);
  }
  public synchronized void decrement(){
    count--;
    System.out.println(Thread.currentThread().getName()+ " Java built-in monitor - count decremented:" + count);
  }
  public synchronized int getCount(){
    System.out.println(Thread.currentThread().getName()+ " Java built-in monitor - count:" + count);
    return count;
  }
}
