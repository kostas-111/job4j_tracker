package ru.job4j.max;

public class Reduce {
    private int[] arrayReduce;

    public void to(int[] array) {
        arrayReduce = array;
    }

    public void print() {
        for (int index = 0; index < arrayReduce.length; index++) {
            System.out.println(arrayReduce[index]);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        Reduce reduce = new Reduce();
        reduce.to(array);
        reduce.print();
    }
}
