package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping
 * fileName : R2dbcObjectCreationWithOnlyOneConstructorExample
 * author : taeil
 * date : 4/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/8/24        taeil                   최초생성
 */
@Slf4j
@Data
public class R2dbcObjectCreationWithOnlyOneConstructorExample {
    @Id
    private Long id;
    private String name;
    private int age;
    private String gender;

    public R2dbcObjectCreationWithOnlyOneConstructorExample(String name, int age) {
        log.info("constructor(name, age) is called");
        this.name = name;
        this.age = age;
    }

}