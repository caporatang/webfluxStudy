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
 * fileName : R2dbcObejctCreationwithPersistenceCreatorExample
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
public class R2dbcObjectCreationWithPersistenceCreatorExample {
    // Object Mapping 테스트 해보기
    // @PersistenceCreator을 갖는 constructor가 존재
    // NoArgsConstructor, AllArgsConstructor 전부 패스

    @Id
    private Long id;
    private String name;
    private int age;
    private String gender;

    public R2dbcObjectCreationWithPersistenceCreatorExample() {
        log.info("No-args constructor is called");
    }

    @PersistenceCreator
    public R2dbcObjectCreationWithPersistenceCreatorExample(String name, int age) {
        log.info("constructor(name, age) is called");
        this.name = name;
        this.age = age;
    }

    @PersistenceCreator
    public R2dbcObjectCreationWithPersistenceCreatorExample(Long id, String name, int age) {
        // 여러 개가 존재한다면 가장 마지막 - PersistenceCreator가 붙은 constructor를 사용
        // PersistenceCreator constructor는 하나만
        log.info("constructor(id, name, age) is called");
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public R2dbcObjectCreationWithPersistenceCreatorExample(Long id, String name, int age, String gender) {
        log.info("constructor (id, name, age, gender) is called");
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

}