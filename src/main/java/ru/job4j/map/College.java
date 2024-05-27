package ru.job4j.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class College {
    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        Optional<Student> result = students.keySet()
                .stream()
                .filter(student -> student.account().equals(account))
                .findFirst();
        return result.isPresent() ? result : Optional.empty();
    }

    public Optional<Subject> findBySubjectName(String account, String name) {
       Optional<Student> student = findByAccount(account);
        if (student.isPresent()) {
            Optional<Subject> result = students.get(student)
                    .stream()
                    .filter(subject -> subject.name().equals(name))
                    .findFirst();
            return result.isPresent() ? result : Optional.empty();
        }
        return Optional.empty();
    }
}