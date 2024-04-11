package com.example.springdatareactive.r2dbc.entityOperations;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

/**
 * packageName : com.example.springdatareactive.r2dbc.entityOperations
 * fileName : R2dbcEntityTemplateInsertExample
 * author : taeil
 * date : 4/11/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/11/24        taeil                   최초생성
 */
@Slf4j
public class R2dbcEntityTemplateInsertExample {
    // into를 통해서 insert할 table 명시
    // entity를 생성하여 using에 전달.

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

        var newPerson =new PersonEntity(null, "taeil", 29, "M");

        r2dbcEntityTemplate.insert(PersonEntity.class)
                .into("person")
                .using(newPerson)
                .doOnNext(it -> log.info("person: {}", it))
                .subscribe();
    }

}