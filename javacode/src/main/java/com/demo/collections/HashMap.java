package com.demo.collections;

import java.util.Map;

public class HashMap {

    public static void main(String[] args) {
        Map<String,String> map = new java.util.HashMap<>();



        map.put("123","123");
        map.put("222","222");


        map.keySet().forEach(x -> {
            System.out.printf("key: %s,value:%s%n", x, map.get(x));
        });
    }
}
