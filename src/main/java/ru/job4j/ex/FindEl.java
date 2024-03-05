package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int result = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                result = i;
            }
        }
        if (result == -1) {
            throw new ElementNotFoundException("No such element in array.");
        }
        return result;
    }

    public static void main(String[] args) {
        String[] array = {"man", "child", "spyder"};
        try {
            indexOf(array, "cat");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}