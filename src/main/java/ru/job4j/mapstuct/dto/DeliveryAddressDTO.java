package ru.job4j.mapstuct.dto;

import lombok.*;

/*
Преобразование нескольких entity в одно DTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryAddressDTO {
    private String name;
    private int houseNumber;
    private String city;
    private String state;
}
