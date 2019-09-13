package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

//todo validation
/*
JSON, представляющий объект Car
{
id: Long (not null),
model: String (not null, в формате vendor-model например BMW-X5,
    причем vendor никогда не содержит “-” и не пустой, model не пустой),
horsepower: Integer (not null),
ownerId: Long (not null)
}

Все поля удовлетворяют ограничениям на тип и формат
horsepower > 0
ранее валидный объект с таким id не передавался
существует Person с Id=ownerId
данный Person старше 18 лет
*/

//todo ограничения БД
public class CarDto {
    Long id;
    //todo (not null, в формате vendor-model например BMW-X5,
    // причем vendor никогда не содержит “-” и не пустой,
    // model не пустой),

    //"vendor-model" = "BMW-X5",
    //patern = "?-?" где "?"=как минимум один символ
    String model; //=bad name var
    Integer horsepower;
    Long ownerId;
}
