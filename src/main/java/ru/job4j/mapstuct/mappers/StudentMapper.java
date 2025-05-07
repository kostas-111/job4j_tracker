package ru.job4j.mapstuct.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.mapstuct.dto.StudentDto;
import ru.job4j.mapstuct.model.StudentEntity;


/*
Аннотация @Mapper сообщает компилятору, что этот интерфейс относится к библиотеке MapStruct.
Библиотека сама сопоставляет поля с одинаковым названием, но так как у нас наименование
 одного из полей не совпадает, то с помощью аннотации
@Mapping(target="className", source="classVal") мы указываем, какие поля использовать,
то же самое и при обратной трансформации:
@Mapping(target="classVal", source="className")
Если бы наименования полей совпадали бы, то аннотации@Mapping нам бы не понадобилось.
 */
@Mapper
public interface StudentMapper {

    @Mapping(target = "className", source = "classVal")
    StudentDto getModelFromEntity(StudentEntity student);

    @Mapping(target = "classVal", source = "className")
    StudentEntity getEntityFromDto(StudentDto studentDto);
}