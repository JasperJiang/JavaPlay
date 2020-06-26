package com.demo.thread;

public class RunnableThread implements Runnable {


    @Override
    public void run() {
        System.out.println("this is Runnable thread start ...");


        try {
            Thread.sleep(10000);
            System.out.println("this is Runnable thread stop ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
