# ReentrantLock
ReentrantLock类是属于java.util.concurrent的。实现了Lock, java.io.Serializable两个接口，是一个可重入的互斥锁，所谓可重入是线程可以重复获取已经持有的锁。
```java
    /**
     * Creates an instance of {@code ReentrantLock}.
     * This is equivalent to using {@code ReentrantLock(false)}.
     */
    public ReentrantLock() {
        sync = new NonfairSync();
    }

    /**
     * Creates an instance of {@code ReentrantLock} with the
     * given fairness policy.
     *
     * @param fair {@code true} if this lock should use a fair ordering policy
     */
    public ReentrantLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
    }
    abstract static class Sync extends AbstractQueuedSynchronizer
```

ReentrantLock实现锁的机制是通过Sync进行操作的。Sync类是继承AbstractQueuedSynchronizer类的。这也就表明ReentrantLock是基于AQS的实现的。‘

Sync,FairSync和NonFairSync都是ReentrantLock的静态内部类。FairSync和NonFairSync又是Sync具体实现类，分别对应的是公平锁和非公平锁，公平主要是指按照FIFO原则顺序获取锁，非公平可以根据定义的规则来选择获得锁。

## NonFairSync 源码分析
非公平锁NonFairSync是ReentrantLock默认的实现方式。这里可以看一下它的lock实现过程：

```java
/**
 * Performs lock.  Try immediate barge, backing up to normal
 * acquire on failure.
 */
final void lock() {
    if (compareAndSetState(0, 1))
        setExclusiveOwnerThread(Thread.currentThread());
    else
        acquire(1);
}
```

首先通过CAS更新state状态，如果更新成功，则获取锁，设定当前线程为锁的拥有者。

```java
protected final boolean compareAndSetState(int expect, int update) {
    // See below for intrinsics setup to support this
    return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
}
```

如果更新失败，表明当前锁被其他线程占有。则会调用acquire(1)方法。acquire具体实现如下：
```java
public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
}
```
```java
protected final boolean tryAcquire(int acquires) {
        return nonfairTryAcquire(acquires);
}
```

tryAcquire过程，将再次尝试获取锁，其中tryAcquire在静态内部类NonfairSync类中被重写，具体的实现是Sync的nonfairTryAcquire方法：

```java
final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```
其主要过程是先获取state的值，如果等于0，则通过CAS更新state的值。如果state不为0，则判断当前线程是否是锁的持有者，如果是，则将state加1，返回true。

如果tryAcquire仍然失败的话，首先会调用addWaiter(Node.EXCLUSIVE)，将当前线程加入到等待队列的尾部。然后会调用acquireQueued方法，acquireQueued的作用主要是用来阻塞线程的：

```java
/**
 * Acquires in exclusive uninterruptible mode for thread already in
 * queue. Used by condition wait methods as well as acquire.
 *
 * @param node the node
 * @param arg the acquire argument
 * @return {@code true} if interrupted while waiting
 */
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            final Node p = node.predecessor();
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

这里是一个循环自旋操作，在阻塞线程之前，首先判断线程自身前面的节点是否是head节点，如果是，则重新去获取锁，获取成功后，返回，并取消不断获取的过程。如果不是，调用shouldParkAfterFailedAcquire方法去判断是否应该阻塞当前线程，主要是通过节点的waitStatus来进行判断。

## FairSync 源码分析

公平锁FairSync和非公平锁NonFairSync的实现很相似，这里比较一下两者的差别。

FairSync的lock方法中没有像NonFairSync中先去通过CAS操作state去获取锁，而是直接通过tryAcquire去获取锁。
```java
final void lock() {
    acquire(1);
}
```
FairSync版本tryAcquire在获取锁的过程中，需要先判断队列中是否有其他等待的线程，如果没有，才回去尝试获取锁。

```java
/**
 * Fair version of tryAcquire.  Don't grant access unless
 * recursive call or no waiters or is first.
 */
protected final boolean tryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        if (!hasQueuedPredecessors() &&
            compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0)
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```
## unlock() 释放锁
释放锁没有区分公平和非公平的。主要的工作就是减小state的值。当state等0的时候，释放锁并唤醒队里中其他线程来获取锁。

```java
public void unlock() {
    sync.release(1);
}
```

```java
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```

```java
protected final boolean tryRelease(int releases) {
    int c = getState() - releases;
    if (Thread.currentThread() != getExclusiveOwnerThread())
        throw new IllegalMonitorStateException();
    boolean free = false;
    if (c == 0) {
        free = true;
        setExclusiveOwnerThread(null);
    }
    setState(c);
    return free;
}
```
## 总结
ReentrantLock是通过AQS的state字段来判断所是否被占用。  
公平与非公平的差别是在于获取锁的方式是否是按照顺序的。  
state操作是通过CAS实现的。通过队列来实现因抢占锁被阻塞的队列。  
在阻塞线程的过程中，AQS有自旋的过程，并非是获取不到锁就直接阻塞。  
