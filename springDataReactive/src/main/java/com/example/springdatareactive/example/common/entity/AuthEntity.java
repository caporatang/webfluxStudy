package com.example.springdatareactive.example.common.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * packageName : com.example.springdatareactive.example.common.entity
 * fileName : AuthEntity
 * author : taeil
 * date : 4/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/13/24        taeil                   최초생성
 */
@Data
@Table("AUTH")
public class AuthEntity {
    @Id
    private Long id;
    private final Long userId;
    private final String token;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PersistenceCreator
    public AuthEntity(Long id, Long userId, String token) {
        this.id = id;
        this.userId = userId;
        this.token = token;
    }


    public AuthEntity(Long userId, String token){
        this(null, userId, token);
    }
}