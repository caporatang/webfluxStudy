package com.example.springdatareactive.r2dbc.entityOperations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName : com.example.springdatareactive.r2dbc.entityOperations
 * fileName : PersonEntity
 * author : taeil
 * date : 4/11/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/11/24        taeil                   최초생성
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonEntity {
    private Long id;
    private String name;
    private int age;
    private String gender;
}