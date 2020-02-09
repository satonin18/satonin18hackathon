package ipr.web.dto.input.valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import ipr.web.helper.PropertiesApp;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class PersonDto {
//    без groups = {New.class}, чтоб код был чище
//    interface New {
//    }



    //такой id не дожен лежать в бд //проверка в контроллере/сервисе
//    @NotNull
    Long id;



//    @NotNull
//    @Size(max = 100)
    String name;



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PropertiesApp.DATA_FORMAT_BIRTHDATE)
    @JsonDeserialize(using = LocalDateDeserializer.class)

//    @NotNull //can be remove
//    @Past

    LocalDate birthdate;

}
