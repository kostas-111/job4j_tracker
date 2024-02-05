package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активность: " + active);
        System.out.println("Номер ошибки: " + status);
        System.out.println("Комментарий: " + message);
    }

    public static void main(String[] args) {
        Error defaultEr = new Error();
        Error arithmeticEx = new Error(true, 1, "Попытка целочисленного деления на нуль");
        Error emptyStack = new Error(false, 2, "Попытка извлечения объекта из пустого стека");
        defaultEr.printInfo();
        arithmeticEx.printInfo();
        emptyStack.printInfo();
    }
}