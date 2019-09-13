package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor

//todo validation
/*
JSON, представляющий объект Person
{
id: Long (not null),
name: String (not null),
birthdate: Date (not null,формат dd.MM.yyyy),
}

Все поля удовлетворяют ограничениям на тип и формат
Дата рождения в прошлом
Дата рождения в нужном формате
Ранее валидный объект с таким id не передавался
*/

public class PersonWithCars { //can be extends Person
    long id;
    String name;
    Date birthdate;//todo dd.MM.yyyy

    List<Car> cars; //Array of Cars [{}, {}, ...] (not null)
}
