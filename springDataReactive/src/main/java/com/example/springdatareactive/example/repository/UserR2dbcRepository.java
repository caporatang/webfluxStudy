package com.example.springdatareactive.example.repository;

import com.example.springdatareactive.example.common.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

/**
 * packageName : com.example.springdatareactive.example.repository
 * fileName : UserR2dbcRepository
 * author : taeil
 * date : 4/12/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/12/24        taeil                   최초생성
 */
public interface UserR2dbcRepository extends R2dbcRepository<UserEntity, Long> {


}