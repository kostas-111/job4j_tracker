package ru.job4j.mapstuct.dto;

import lombok.*;

/*
DTO для объединения информации из сущностей
SubjectEntity и StudentSubject
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentSubjectDto {
    private int id;
    private String name;
    private String className;
    private String subject;
}