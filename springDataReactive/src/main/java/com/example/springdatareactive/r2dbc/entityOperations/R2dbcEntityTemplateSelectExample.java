package com.example.springdatareactive.r2dbc.entityOperations;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;

/**
 * packageName : com.example.springdatareactive.r2dbc.entityOperations
 * fileName : R2dbcEntityTemplateSelectExample
 * author : taeil
 * date : 4/11/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/11/24        taeil                   최초생성
 */
@Slf4j
public class R2dbcEntityTemplateSelectExample {
    // ConnectionFactory를 이용하여 R2dbcEntityTemplate을 생성
    // Query와 Criteria를 이용해서 query 생성
    // PersonNameOnly class를 이용해서 Name만 Projection

    public static void main(String[] args) {
        var config = MySqlConnectionConfiguration.builder()
                .host("localhost")
                .port(3306)
                .username("root")
                .password("1234")
                .database("DBTest")
                .build();

        var connectionFactory = MySqlConnectionFactory.from(config);
        var r2dbcEntityTemplate = new R2dbcEntityTemplate(connectionFactory);

        var query = Query.query(
                Criteria.where("name").is("taeil")
        );

        new PersonEntity(null, "taeil", 20, "M");

        r2dbcEntityTemplate.select(PersonEntity.class)
                .from("person")
                .as(PersonNameOnly.class)// Person에서 name 필드만 가지고 있는 SubClass
                .matching(query)
                .first()
                .doOnNext(it -> log.info("person : {}", it))
                .subscribe();

    }

}