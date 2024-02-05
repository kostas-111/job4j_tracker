package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int z) {
        return sum(z) + multiply(z) + minus(z) + divide(z);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(5);
        System.out.println(result);
        System.out.println("Результат работы метода minus: " + minus(2));
        System.out.println("Результат работы метода divide: " + calculator.divide(15));
        System.out.println("Результат работы метода sumAllOperation: " + calculator.sumAllOperation(8));
    }
}