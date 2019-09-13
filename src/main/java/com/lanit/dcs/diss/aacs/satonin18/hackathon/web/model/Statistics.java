package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor

//todo validation
/*

JSON, представляющий объект Statistics
{
personcount: Long , - количество объектов Person, прошедших валидацию и сохраненных
carcount: Long,  - количество объектов Car, прошедших валидацию и сохраненных
uniquevendorcount: Long - количество уникальных производителей среди прошедших валидацию и сохраненных объектов Car, регистронезависимо
}

Все поля удовлетворяют ограничениям на тип и формат
Дата рождения в прошлом
Дата рождения в нужном формате
Ранее валидный объект с таким id не передавался
*/

public class Statistics { //can be extends Person
    long personcount;
    long carcount;
    long uniquevendorcount; //регистронезависимо
}
