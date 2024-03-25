package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullSearch {
    public Set<String> extractNumber(List<Task> tasks) {
        Set<String> numbers = new HashSet<>();
        for (Task extract : tasks) {
            numbers.add(extract.getNumber());
        }
        return numbers;
    }
}