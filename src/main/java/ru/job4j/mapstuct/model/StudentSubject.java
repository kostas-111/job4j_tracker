package ru.job4j.mapstuct.model;

import lombok.*;

/*
Как видим, модель StudentSubject в качестве поля
содержит другую модель - SubjectEntity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentSubject {
    private int id;
    private String name;
    private String classVal;
    private SubjectEntity subject;
}
