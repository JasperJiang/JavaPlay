package com.demo.collections.DelayQueue;

import java.util.concurrent.DelayQueue;

public class DelayMain {

    public static void main(String[] args) {

        DelayQueue<Task> delayQueue = new DelayQueue<>();


        delayQueue.add(new Task(5,1));
        delayQueue.add(new Task(5,2));
        delayQueue.add(new Task(5,3));
        delayQueue.add(new Task(5,4));

        while (true){
            try {
                Task task = delayQueue.take();
                System.out.println(task.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (delayQueue.isEmpty()) break;
        }
    }
}
