package org.example;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class PairEx1 {
    public static void main(String[] args) {
        AbstractMap.SimpleEntry<Integer, String> entry = new AbstractMap.SimpleEntry<>(1, "one");
        System.out.println(entry);
        AbstractMap.SimpleEntry<Integer, String> e2 = new AbstractMap.SimpleEntry<>(1, "one");

        Map<AbstractMap.SimpleEntry<Integer, String>, Integer> map = new HashMap<>();
        map.put(entry, 1);
        map.put(e2, 2);
        System.out.println(map);
    }
}
