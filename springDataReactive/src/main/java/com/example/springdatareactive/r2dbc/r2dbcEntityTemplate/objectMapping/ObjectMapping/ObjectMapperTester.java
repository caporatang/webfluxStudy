package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping
 * fileName : ObjectMapperTester
 * author : taeil
 * date : 4/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/8/24        taeil                   최초생성
 */
@Slf4j
public class ObjectMapperTester {
    public static void main(String[] args) {
    // R2dbcEntityTemplate의 select 호출시 R2dbcConverter를 사용하기 때문에 이를 이용해서 select에 class를 넘기는 방식으로 테스트한다.
    var config = MySqlConnectionConfiguration.builder()
            .host("localhost")
            .port(3306)
            .username("root")
            .password("1234")
            .database("DBTest")
            .build();

    var connectionFactory = MySqlConnectionFactory.from(config);
    var template = new R2dbcEntityTemplate(connectionFactory);

        template.select(PersonWithMultipleConstructor.class)
            .from("person")
            .first()
            .doOnNext(it -> log.info("person : {}", it))
            .block();
    }
}