package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
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

    //"vendor-model" = "BMW-X5" //vendor никогда не содержит “-” и не пустой, model не пустой
    @NotNull
//    @NotBlank
    @Pattern(regexp = "^[^-]+-.+$") //^=start  $=end  [^-]=любой_символ_кромеТИРЕ  .=любой_символ  +=один_или_более_ раз
    String model; //=bad name var

    @NotNull
//    @Positive
    Integer horsepower;

    @NotNull
    Long ownerId;
}
