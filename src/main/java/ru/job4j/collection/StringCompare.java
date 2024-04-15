package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
       int result = Character.compare(left.charAt(0), right.charAt(0));
       int len = Math.min(right.length(), left.length());
       for (int i = 0; i < len; i++) {
           result = Character.compare(left.charAt(i), right.charAt(i));
           if (result != 0) {
               break;
           }
        }
        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}