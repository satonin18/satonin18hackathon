package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

//todo validation
/*
JSON, представляющий объект Car
{
id: Long (not null),
entity: String (not null, в формате vendor-entity например BMW-X5,
    причем vendor никогда не содержит “-” и не пустой, entity не пустой),
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
// TODO SET VALIDATOR

@Data
@NoArgsConstructor
public class CarDto4save {

    @NotNull
    Long id;
    //todo (not null, в формате vendor-entity например BMW-X5,
    // причем vendor никогда не содержит “-” и не пустой,
    // entity не пустой),

    //"vendor-entity" = "BMW-X5",
    //patern = "?-?" где "?"=как минимум один символ
    @NotNull
    @Pattern(regexp = "^.+-.+$") //^=start $=end .=любой_символ +=один_или_более_ раз
    String model; //=bad name var

    @NotNull
    Integer horsepower;

    @NotNull //todo положительное
    Long ownerId;
}
