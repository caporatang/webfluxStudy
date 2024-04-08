package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping
 * fileName : R2dbcObjectCreationWithNoArgsConstructorExample
 * author : taeil
 * date : 4/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/8/24        taeil                   최초생성
 */
@Data
@Slf4j
public class R2dbcObjectCreationWithNoArgsConstructorExample {
    // No-args constructor가 존재한다면 해당 constructor를 사용한다.
    // 다른 constructor는 패스

    @Id
    private Long id;
    private String name;
    private int age;
    private String gender;

    public R2dbcObjectCreationWithNoArgsConstructorExample() {
        log.info("No-args constructor is called");
    }

    public R2dbcObjectCreationWithNoArgsConstructorExample(String name, int age) {
        log.info("constructor(name, age) is called");
        this.name = name;
        this.age = age;
    }

    public R2dbcObjectCreationWithNoArgsConstructorExample(Long id, String name, int age, String gender) {
        log.info("constructor (id, name, age, gender) is called");
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}