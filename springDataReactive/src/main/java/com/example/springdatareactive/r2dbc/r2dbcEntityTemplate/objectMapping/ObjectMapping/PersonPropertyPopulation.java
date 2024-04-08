package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.objectMapping.ObjectMapping
 * fileName : PersonPropertyPopulation
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
public class PersonPropertyPopulation {
    // 공식 문서에서는 with 메소드를 사용하는 법, setter 사용하는 법을 언급하고 있다.
    // r2dbc에서는 property가 mutable 할때만 property population 적용
    // Object creation '하나의 constructor' 조건에 따라서 id와 name에만 값을 추가
    // property를 순회하며 mutable한 경우에만 reflection을 사용해서 값 주입
    // property가 immutable인 경우, 현재는 방법이 없다.

    @Id
    private Long id;
    private String name;
    private int age;
    private String gender;

    public PersonPropertyPopulation(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}