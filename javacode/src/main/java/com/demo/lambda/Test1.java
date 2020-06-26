package com.demo.lambda;

public class Test1 {


    static void a(String b){
        System.out.println(b);
    }


    public interface Predicate<T>{
        void execute(T t);
    }

    static void wrapper(Predicate<String> a){
        System.out.println("ahead");
        a.execute("2222");
        System.out.println("behind");
    }

    public static void main(String[] args) {
        wrapper(Test1::a);
    }

}
