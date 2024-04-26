package ru.job4j.collection;

import java.util.Arrays;

public class MinDifferenceBetweenNumbers {
    public static int minDifference(int[] arrayNum) {
        Arrays.sort(arrayNum);
        int result = Math.abs(arrayNum[0] - arrayNum[1]);
        for (int i = 1; i < arrayNum.length - 1; i++) {
            int dif = Math.abs(arrayNum[i] - arrayNum[i + 1]);
             if (result > dif) {
                 result = dif;
             }
        }
        return result;
    }
}