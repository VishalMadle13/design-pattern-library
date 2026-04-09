package dev.hashnode.systemdesigner.concurrency.monitor;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// Explicit Monitor
public class BoundedCounter {
  private int count = 0;
  private final int max;
  private final Lock lock = new ReentrantLock();
  private final Condition notZero = lock.newCondition();
  private final Condition notFull = lock.newCondition();
  public BoundedCounter(int max) {
    this.max = max;
  }
  public void increment() throws InterruptedException{
    lock.lock();
    try{
      while (count == max){
        notFull.await();
      }
      count++;
      System.out.println(Thread.currentThread().getName()+ " monitor - count incremented:" + count);
      notZero.signal();
    }
    finally{
      lock.unlock();
    }
  }

  public void decrement() throws InterruptedException{
    lock.lock();
    try {
      while (count == 0){
        notZero.await();
      }
      count--;
      System.out.println(Thread.currentThread().getName()+ " monitor - count decremented:" + count);
      notFull.signal();
    }
    finally {
      lock.unlock();
    }
  }
}
