package com.demo.collections;

import java.util.*;
import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> m = new HashMap<>();
        m.put("a", 1);
        m.put("b", 2);
        System.out.println("a:" + m.get("a"));
        System.out.println("b:" + m.get("b"));
        m.put("a", 3);
        System.out.println("a:" + m.get("a"));
        System.out.println(m.keySet());
        System.out.println(m.values());

        System.out.println("-----map转list------");
        List<Integer> l = new ArrayList<Integer>(m.values());
        System.out.println(l);
        System.out.println("-----list转set------");
        Set<Integer> s = new HashSet<Integer>(l);
        System.out.println(s);
        System.out.println("-----将list做一个排序------");
        Collections.sort(l);
        System.out.println(l);
    }
}
