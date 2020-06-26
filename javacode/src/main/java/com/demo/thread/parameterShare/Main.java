package com.demo.thread.parameterShare;

public class Main {


    public static void main(String[] args) {

        MyThread myThread = new MyThread();


        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);

        t1.start();
        t2.start();
    }

}
