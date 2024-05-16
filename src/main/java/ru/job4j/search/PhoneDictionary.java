package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<String> predicate = string -> string.contains(key);
        Predicate<Person> combine = person -> predicate.test(person.getName()) || predicate.test(person.getSurname())
                || predicate.test(person.getPhone()) || predicate.test(person.getAddress());
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}