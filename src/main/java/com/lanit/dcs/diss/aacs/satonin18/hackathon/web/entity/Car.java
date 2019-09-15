package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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

//todo ограничения БД (по идее они уже прописаны в DTO на более высоком уровне приложения)

@Data
@NoArgsConstructor

@Entity
@Table(name = "cars")

//@JsonIgnoreProperties({"person", "vendor", "model"}) //todo in "model" = vendor-model
public class Car {

    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @JsonIgnore

//    @NotBlank

    @Column(name = "vendor", nullable = false, length = 500)
    String vendor;


    @JsonIgnore

//    @NotBlank

    @Column(name = "model", nullable = false, length = 500)
    String model;


    @JsonProperty("model")
    String model(){
        return String.join("-",vendor, model);
    }


    @Positive
//    @Range(min=1)

    @Column(name = "horsepower", nullable = false)
    Integer horsepower;


    @Column(name = "ownerId", nullable = false)
    Long ownerId;


//    @JsonIgnore
//
//    @ManyToOne
//    @JoinColumn(name = "ownerId", referencedColumnName = "id", nullable = false)
//    private Person person;
}
