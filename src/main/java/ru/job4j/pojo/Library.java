package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book java = new Book("Clean code", 500);
        Book algo = new Book("Grokking Algorithms", 287);
        Book invest = new Book("The intelligent investor", 565);
        Book psyhology = new Book("Thinking, fast and slow", 658);
        Book[] books = new Book[4];
        books[0] = java;
        books[1] = algo;
        books[2] = invest;
        books[3] = psyhology;
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + " - " + books[i].getAmount());
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        System.out.println("Books after replace.");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + " - " + books[i].getAmount());
        }
        System.out.println("Only books named \"Clean code\"");
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals(java.getName())) {
                System.out.println(books[i].getName() + " - " + books[i].getAmount());
            }
        }
    }
}