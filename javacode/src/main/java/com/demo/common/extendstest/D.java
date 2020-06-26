package com.demo.common.extendstest;

public class D{
    public static void main(String[] args) {
//      C c = new B(); //报错
        C c = (C) new B();
        c.run();
    }
}

class A {
}

class B extends A{

    public void run(){
        System.out.println("b");
    }

}

class C extends B{

}
