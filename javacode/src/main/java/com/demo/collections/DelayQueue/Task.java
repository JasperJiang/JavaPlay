package com.demo.collections.DelayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class Task implements Delayed {
    /**
     * 到期时间
     */
    private final long time;

    private final long startTime;

    private final int id;

    public int getId() {
        return id;
    }

    public Task(long time, int id) {
        this.time =TimeUnit.NANOSECONDS.convert(time,TimeUnit.SECONDS);
        this.startTime = System.nanoTime();
        this.id = id;
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startTime + time - System.nanoTime() ,  TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(o == null) return 1;
        if(o == this) return 0;
        Task s = (Task)o;
        if (this.time > s.time) {
            return 1;
        }else if (this.time == s.time) {
            return 0;
        }else {
            return -1;
        }
    }
}