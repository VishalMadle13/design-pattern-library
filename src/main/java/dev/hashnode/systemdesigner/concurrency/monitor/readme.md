# Monitor (concurrency pattern)

A **monitor** is one object that holds **shared data** plus **synchronization** (a lock and optional **condition variables**). Threads use the object’s methods; the lock and waiting rules stay **inside** the object, so callers do not manage locks themselves.
 
---

## What you get

| Guarantee | Meaning |
|-----------|---------|
| Mutual exclusion | Only **one** thread runs a guarded method (critical section) at a time on that monitor. |
| Coordination | A thread can **wait** until some condition is true, then **continue** when another thread **signals** that things changed. |

**What sits inside a monitor (plain view):**

```
              +---------------------------+
              |     Monitor object      |
              |                         |
              |   * Shared data         |
              |   * Lock (one at a time)|
              |   * Condition queue(s)  |
              +---------------------------+
                    ^            ^
               Thread A     Thread B
            (has the lock) (blocked until lock free)
```

---

## How it works (short story)

1. A thread **enters** a monitor method and **acquires** the lock.
2. It reads/writes shared data **safely** (no other thread is inside at the same time).
3. If the data is not in the right state yet, it **waits** on a condition (and **releases** the lock so others can run).
4. Another thread **changes** the data and **signals** waiters.
5. A waiting thread **wakes**, **re-acquires** the lock, and checks the condition again (usually in a **`while`** loop).

**Who does what, in order:**

```
  Thread 1              Monitor                 Thread 2
      |                     |                        |
      |-- acquire lock ---->|                        |
      |-- work on data ---->|                        |
      |-- await (condition |                        |
      |   not met) -------->| releases lock          |
      |                     |<----- acquire lock ----|
      |                     |<----- change data -----|
      |                     |<----- signal ---------|
      |<-- wake, re-acquire |                        |
      |    lock, check while|                        |
      |    loop again ----->|                        |
```

---

## Picture: “one room, one door”

- The **room** is the shared object; the **door** is the **lock**. Only one thread is “inside” at a time.
- If the thread inside **needs something that is not ready**, it steps into a **waiting area** (a **condition**). That **opens the door** so another thread can enter and **fix** the state.
- When fixed, a **signal** brings waiters back toward the door queue.

That waiting area is what makes a monitor more than a plain mutex: you can **wait for a specific condition** instead of spinning or blocking the whole system badly.

---

## Where you see monitors in practice

- **`BlockingQueue`** and **`LinkedBlockingQueue`**
- **Thread pools** (work queues + coordination)
- **Connection pools**
- **Logging** pipelines that guard shared buffers or appenders

---

## Java: two common styles

### 1. Built-in monitor: `synchronized`

Every Java object has an **intrinsic lock** and **one** implicit wait set (`wait` / `notify` / `notifyAll` on that object).

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++; // lock on `this` for method duration
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int get() {
        return count;
    }
}
```

`synchronized` on a method means: acquire **`this`** lock on entry, release on exit (including when an exception is thrown). Callers just call `counter.increment()` — they do not pass a lock around.

### 2. Explicit monitor: `ReentrantLock` + `Condition`

Same idea, but you can have **several named condition queues**. Waiters on “not full” are not mixed with waiters on “not empty,” unlike a single `notifyAll()` on the intrinsic monitor.

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedCounter {
    private int count = 0;
    private final int max;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notZero = lock.newCondition();

    public BoundedCounter(int max) {
        this.max = max;
    }

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (count == max) {
                notFull.await();
            }
            count++;
            notZero.signal();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notZero.await();
            }
            count--;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
```

**Why `while` and not `if`?** After `await`, another thread may have changed the state again; you must **re-check** the condition before acting.

---

## You may have used this already

- **`Vector`**, **`Hashtable`**, **`StringBuffer`**: methods were `synchronized` (coarse-grained monitors). Modern code usually prefers other types.
- **`Collections.synchronizedList(...)`**: the list is guarded, but **two calls** like `size()` then `get(i)` are **not** one atomic operation — another thread can change the list in between.
- **`LinkedBlockingQueue`**: a real-world monitor-style design with separate conditions for “not empty” and “not full.”

---

## Trade-off

Monitors often use **one lock per object** for many operations. That is **simple and safe**, but **readers** can block each other even when reads would be safe together. When profiling shows that as a bottleneck, patterns like **`ReadWriteLock`** help — that is a different design, not a classic single-lock monitor.

**Summary:** A monitor = **shared state + lock + (optional) conditions**, with **encapsulated** synchronization so application code stays easy to read and harder to get wrong.
