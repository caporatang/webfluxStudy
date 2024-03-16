package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.converter;

import com.example.springdatareactive.r2dbc.mysql.Person;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate
 * fileName : PersonWriteConverter
 * author : taeil
 * date : 3/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 3/17/24        taeil                   최초생성
 */
@WritingConverter
public class PersonWriteConverter implements Converter<Person, OutboundRow> {
    // Entity를 source로 Row를 target으로 하는 converter
    // OutboundRow에 값을 추가
    // key에는 column의 이름, value에는 Parameter.from을 이용해서 entity의 속성을 전달
    // DefaultDatabaseClient에서 OutboundRow를 이용해서 SQL 생성
    @Override
    public OutboundRow convert(Person source) {
        OutboundRow row = new OutboundRow();
        row.put("id", Parameter.from(source.getId()));
        row.put("name", Parameter.from(source.getName()));
        row.put("age", Parameter.from(source.getAge()));
        row.put("gender", Parameter.from(source.getGender()));

        return row;
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