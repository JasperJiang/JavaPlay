package com.demo.thread.volatilePlay;

public class VolatileTest implements Runnable {

    private ObjectA a; // 加上volatile 就可以正常结束While循环了

    private volatile ObjectA[] b = {new ObjectA()}; //volatile 只保护了引用不保护本身

    public VolatileTest(ObjectA a) {
        this.a = a;
    }

    public ObjectA getA() {
        return a;
    }

    public void setB() {
        this.b[0].setFlag(false);
    }

    public void setB2(ObjectA[] b){
        this.b = b;
    }

    @Override
    public void run() {
        long i = 0;
        while (a.isFlag()) {
            System.out.println(b[0].isFlag());
        }
        System.out.println("stop My Thread " + i);
    }

    public void stop() {
        a.setFlag(false);
    }

    public static void main(String[] args) throws InterruptedException {
        // 如果启动的时候加上-server 参数则会 输出 Java HotSpot(TM) Server VM
        System.out.println(System.getProperty("java.vm.name"));

        VolatileTest test = new VolatileTest(new ObjectA());
        new Thread(test).start();

        Thread.sleep(1000);
//        test.stop();
        test.setB();
        int[] b = {2};
//        test.setB2(b);
        Thread.sleep(2000);
        System.out.println("Main Thread " + test.getA().isFlag());
    }

    static class ObjectA {
        private boolean flag = true;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

    }
}
