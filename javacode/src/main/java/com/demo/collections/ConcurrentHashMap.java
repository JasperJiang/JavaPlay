package com.demo.collections;

import java.util.Map;

public class ConcurrentHashMap {
    public static void main(String[] args) {
        Map<String,String> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<>();

        concurrentHashMap.put("123","123");

    }
}
