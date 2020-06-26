package com.demo.thread.parameterShare;

public class MyThread implements Runnable{

    private static int i = 0;

    public synchronized void increment() {
        System.out.println("thread"+Thread.currentThread().getName()+","+i);
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 5; j++) {
            increment();
        }
    }
}
