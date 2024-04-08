package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping
 * fileName : PersonWithMultipleConstructor
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
public class PersonWithMultipleConstructor {
    // 만약 @PersistenceCreator를 갖는 constructor도 없고 No-args constructor도 없다면 ?
    // exception 발생

    @Id
    private Long id;
    private String name;
    private int age;
    private String gender;

    public PersonWithMultipleConstructor(String name, int age) {
        log.info("constructor(name, age) is called");
        this.name = name;
        this.age = age;
    }

    public PersonWithMultipleConstructor(Long id, String name, int age) {
        log.info("constructor(id, name, age) is called");
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public PersonWithMultipleConstructor(Long id, String name, int age, String gender) {
        log.info("constructor(id, name, age, gender) is called");
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}