package com.example.webfluxeampleimage.entity.common.entity;

import lombok.Data;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.blocking.common
 * fileName : UserEntity
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Data
public class UserEntity {
    // DB 개념으로 사용
    private final String id;
    private final String name;
    private final int age;
    private final String profileImageId;
}