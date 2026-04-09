package dev.hashnode.systemdesigner.concurrency.monitor;

public class Runner implements Runnable {

  public static void main(String[] args) {
    new Runner().run();
  }

  @Override
  public void run() {
    demonstrateBuiltInMonitor();
    demonstrateExplicitMonitor();
  }

  private void demonstrateBuiltInMonitor() {
    Counter counter = new Counter();
    Thread thread1 =
        new Thread(
            () -> {
              for (int i = 0; i < 5; i++) {
                counter.increment();
              }
            },
            "inc-worker");
    Thread thread2 =
        new Thread(
            () -> {
              for (int i = 0; i < 5; i++) {
                counter.decrement();
              }
            },
            "dec-worker");
    Thread thread3 =
        new Thread(
            () -> {
              counter.getCount();
              counter.increment();
              counter.getCount();
              counter.decrement();
              counter.getCount();
            },
            "mix-worker");

    startAndJoin(thread1, thread2, thread3);
    System.out.println("--- built-in monitor demo complete ---");
    counter.getCount();
  }

  private void demonstrateExplicitMonitor() {
    BoundedCounter boundedCounter = new BoundedCounter(5);
    Thread[] threads = new Thread[6];
    for (int i = 0; i < 3; i++) {
      final int id = i;
      threads[i] =
          new Thread(
              () -> {
                for (int j = 0; j < 3; j++) {
                  try {
                    boundedCounter.increment();
                  } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                  }
                }
              },
              "bounded-inc-" + id);
    }
    for (int i = 0; i < 3; i++) {
      final int id = i;
      threads[i + 3] =
          new Thread(
              () -> {
                for (int j = 0; j < 3; j++) {
                  try {
                    boundedCounter.decrement();
                  } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                  }
                }
              },
              "bounded-dec-" + id);
    }

    startAndJoin(threads);
    System.out.println("--- explicit monitor (BoundedCounter) demo complete ---");
  }

  private static void startAndJoin(Thread... threads) {
    for (Thread t : threads) {
      t.start();
    }
    for (Thread t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
}
