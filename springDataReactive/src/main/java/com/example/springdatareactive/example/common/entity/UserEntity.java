package com.example.springdatareactive.example.common.entity;

import com.example.springdatareactive.example.common.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

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

@AllArgsConstructor // 직접 제공하면 성능이 좋아진다 -> 이유 찾아보자
@Table("USER")
@Data
public class UserEntity {
    @Id
    private Long id;
    private final String name;
    private final int age;
    private final String profileImageId;
    private final String password;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updateAt;

    @PersistenceCreator
    public UserEntity(Long id, String name, Integer age, String profileImageId, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.profileImageId = profileImageId;
        this.password = password;
    }

    public UserEntity(String name, Integer age, String profileImageId, String password) {
        this(null, name, age,profileImageId, password);
    }



}