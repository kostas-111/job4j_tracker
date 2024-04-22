package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] l = left.split("/");
        String[] r = right.split("/");
        int result = r[0].compareTo(l[0]);
        if (r[0].compareTo(l[0]) == 0) {
            result = left.compareTo(right);
        }
        return result;
    }
}