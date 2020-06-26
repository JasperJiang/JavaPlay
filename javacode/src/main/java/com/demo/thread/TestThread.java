package com.demo.thread;

public class TestThread {


    public static void main(String[] args) throws InterruptedException {

        ThreadClassThread threadClassThread = new ThreadClassThread();

        threadClassThread.start();

        RunnableThread runnableThread = new RunnableThread();
        Thread thread = new Thread(runnableThread);
        thread.run();

    }
}
