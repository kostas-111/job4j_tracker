package ru.job4j.collection;

import java.util.HashSet;
import java.util.Set;

public class UniqueNumbers {
    public static Set<Integer> unigueNumFromArray(int[] numArray) {
        Set<Integer> uniqNum = new HashSet<>();
        Set<Integer> duplicateNum = new HashSet<>();
        for (int number: numArray) {
            if (!uniqNum.add(number)) {
                duplicateNum.add(number);
            }
        }
       uniqNum.removeAll(duplicateNum);
        return uniqNum;
    }
}