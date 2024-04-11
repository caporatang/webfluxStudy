package com.example.springdatareactive.r2dbc.mysqlConvertJava;

import com.example.springdatareactive.r2dbc.mysql.Person;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import dev.miku.r2dbc.mysql.MySqlRow;
import io.r2dbc.spi.Row;

/**
 * packageName : com.example.springdatareactive.r2dbc.mysqlConvertJava
 * fileName : PersonReadConverter
 * author : taeil
 * date : 4/11/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/11/24        taeil                   최초생성
 */
public class PersonReadConverter implements Converter<Row, Person> {

    @Override
    public Person convert(Row source) {
        Long id = source.get("id", Long.class);
        String name = source.get("name", String.class);
        Integer age = source.get("age", Integer.class);
        String gender = source.get("gender", String.class);

        return new Person(id, name,age,gender);

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