package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

//todo ограничения БД
// TODO SET VALIDATOR

@Data
@NoArgsConstructor
public class PersonDto4save {

    @NotNull
    Long id;

    @NotNull
    String name;

//    Date birthdate;

    @NotNull
//    @Pattern(regexp = )
    String birthdate;//todo + PropertiesApp.DATA_FORMAT_BIRTHDATE
}
