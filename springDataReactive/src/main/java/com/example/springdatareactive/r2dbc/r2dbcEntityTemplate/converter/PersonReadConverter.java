package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.converter;

import com.example.springdatareactive.r2dbc.mysql.Person;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import io.r2dbc.spi.Row;
import org.springframework.data.convert.ReadingConverter;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate
 * fileName : PersonReadConverter
 * author : taeil
 * date : 3/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 3/17/24        taeil                   최초생성
 */
@ReadingConverter
public class PersonReadConverter implements Converter<Row, Person> {
    // ReadingConverter
    // Row를 source로 Entity를 target으로 하는 converter
    // Row로부터 name 혹은 index로 column에 접근할 수 있고, 변환하고자 하는 type을 Class로 전달한다.

    @Override
    public Person convert(Row source) {
        Long id = source.get("id", Long.class);
        String name = source.get("name", String.class);
        Integer age = source.get("age", Integer.class);
        String gender = source.get("gender", String.class);

        return new Person(id, name, age, gender);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }


}