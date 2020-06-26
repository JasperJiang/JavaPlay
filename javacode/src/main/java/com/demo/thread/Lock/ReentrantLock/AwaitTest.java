package com.demo.thread.Lock.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitTest {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void testMethod() {

        try {
            lock.lock();
            System.out.println("开始wait");
            condition.await();
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()
                        + (" " + (i + 1)));
            }
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }

    public void signal()
    {
        try
        {
            lock.lock();
            condition.signal();
        }
        finally
        {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        AwaitTest awaitTest = new AwaitTest();

        Thread a = new Thread("123"){
            @Override
            public void run(){
                awaitTest.testMethod();
            }
        };
        a.start();


        TimeUnit.SECONDS.sleep(3);

        Thread b = new Thread("234"){
            @Override
            public void run(){
                awaitTest.signal();
            }
        };
        b.start();
    }
}
