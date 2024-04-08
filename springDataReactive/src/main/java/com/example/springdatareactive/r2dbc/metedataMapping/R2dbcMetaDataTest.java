package com.example.springdatareactive.r2dbc.metedataMapping;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

/**
 * packageName : com.example.springdatareactive.r2dbc.metedataMapping
 * fileName : R2dbcMetaDataTest
 * author : taeil
 * date : 4/9/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/9/24        taeil                   최초생성
 */
@Slf4j
public class R2dbcMetaDataTest {
    // update한 후 version이 1 증가
    // update된 결과에서 score를 200으로 변경 -> 데이터베이스에 반영되지는 않지만 변경된 score를 두 번째 update된 결과에 포함
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

        var newPerson = new PersonWithMetadata(null, "caporatang", 20, "M");

        r2dbcEntityTemplate.insert(PersonWithMetadata.class)
                .into("person")
                .using(newPerson)
                .doOnNext(it -> log.info("person : {}", it))
                .flatMap(person -> {
                    person.setScore(200);
                    return r2dbcEntityTemplate.update(person);
                })
                .doOnNext(it -> log.info("person : {}", it))
                .subscribe();

        r2dbcEntityTemplate.insert(PersonWithMetadata.class)
                .into("person")
                .using(newPerson)
                .doOnNext(it -> log.info("person : {}", it))
                .flatMap(person -> {
                    person.setScore(200);
                    person.setVersion(person.getVersion() + 1 );
                    return r2dbcEntityTemplate.update(person);
                })
                .doOnNext(it -> log.info("person : {}", it))
                .subscribe();

    }

}