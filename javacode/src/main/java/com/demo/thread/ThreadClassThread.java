package com.demo.thread;

public class ThreadClassThread extends Thread {


    public void run() {
        System.out.println("Running Thread start ..." );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
        System.out.println("Thread exiting.");
    }
}
