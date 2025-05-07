package ru.job4j.mapstuct.dto;

import lombok.*;

/*
DTO — это объект, который используется для передачи данных между подсистемами приложения.
Он содержит только поля и getter/setter методы для доступа к ним.
DTO не содержит бизнес-логики и используется только для передачи данных.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private int id;
    private String name;
    private String className;
}
