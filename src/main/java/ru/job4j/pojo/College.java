package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Galkin Konstantin Igorevich");
        student.setGroup("job4j");
        student.setDate(new Date());
        System.out.println("ФИО: " + student.getFio() + " Группа: " + student.getGroup() + " Дата поступления: " + student.getDate());
    }
}