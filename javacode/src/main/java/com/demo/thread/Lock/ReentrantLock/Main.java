package com.demo.thread.Lock.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();

        Thread t1 = new Thread(task);

        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();t2.join();

        System.out.println(Task.i);
    }
}
