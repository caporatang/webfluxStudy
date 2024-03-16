package com.example.springdatareactive.r2dbc;

import lombok.*;

/**
 * packageName : com.example.springdatareactive.r2dbc
 * fileName : Person
 * author : taeil
 * date : 3/16/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 3/16/24        taeil                   최초생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    private Long id;
    private String name;
    private int age;
    private String gender;
}