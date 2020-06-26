package com.demo.thread.Lock;

public class TestSynchronized {


    public void test1()
    {
        synchronized(this)
        {
            int i = 5;
            while( i-- > 0)
            {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try
                {
                    this.wait(500);
                }
                catch (InterruptedException ie)
                {
                }
            }
        }
    }

    public synchronized void test2()
    {
        int i = 5;
        while( i-- > 0)
        {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try
            {
                this.wait(500);
            }
            catch (InterruptedException ie)
            {
            }
        }
    }

    public static void main(String[] args)
    {
        final TestSynchronized myt = new TestSynchronized();
        Thread test1 = new Thread(() -> myt.test1(), "test1"  );

        Thread test2 = new Thread(() -> myt.test2(), "test2"  );
        test1.start();
        test2.start();

    }

}
