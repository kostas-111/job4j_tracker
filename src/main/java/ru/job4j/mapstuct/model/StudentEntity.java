package ru.job4j.mapstuct.model;

import lombok.*;


/*
Сущность - Entity - это модель данных, которая используется в приложении для хранения данных,
это основная единица хранения. Как правило, приложение построено вокруг сущности,
и осуществляет изменение данных, хранящихся в сущности.
Сущность, соответственно, сохраняет все изменения, произведенные приложением над ней.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentEntity {
    private int id;
    private String name;
    private String classVal;
}
