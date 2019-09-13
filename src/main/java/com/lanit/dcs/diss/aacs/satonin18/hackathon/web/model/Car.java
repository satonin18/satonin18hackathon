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
model: String (not null, в формате vendor-model например BMW-X5, причем vendor никогда не содержит “-” и не пустой, model не пустой),
horsepower: Integer (not null),
ownerId: Long (not null)
}

Все поля удовлетворяют ограничениям на тип и формат
horsepower > 0
ранее валидный объект с таким id не передавался
существует Person с Id=ownerId
данный Person старше 18 лет
*/

public class Car {
    long id;
    String model; //todo (not null, в формате vendor-model например BMW-X5, причем vendor никогда не содержит “-” и не пустой, model не пустой),
    int horsepower;
    long ownerId;
}
