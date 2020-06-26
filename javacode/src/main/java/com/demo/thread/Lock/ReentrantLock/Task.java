package com.demo.thread.Lock.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Task implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;


    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();  // 看这里就可以
            try {
                i++;
            } finally {
                lock.unlock(); // 看这里就可以
            }
        }
    }
}
