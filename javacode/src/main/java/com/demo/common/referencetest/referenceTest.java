package com.demo.common.referencetest;

public class referenceTest {
    public static void main(String[] args) {
        Person zhangsan = new Person("ZHANG San");
        changePerson(zhangsan);
        zhangsan.printName();
    }


    //传递的是内存地址所以会改变main里的person
    public static void changePerson(Person person) {
        person.changeName("haha");
    }
}

class Person {
    String name = "default";

    public Person(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    void printName() {
        System.out.println(this.name);
    }
}
