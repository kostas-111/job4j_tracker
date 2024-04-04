package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("galkin@rambler.ru", "Galkin Konstantin");
        myMap.put("jonny@mail.com", "Jonny B");
        for (String key : myMap.keySet()) {
            System.out.println(key + " = " + myMap.get(key));
        }
    }
}