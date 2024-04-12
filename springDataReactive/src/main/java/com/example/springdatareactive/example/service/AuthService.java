package com.example.springdatareactive.example.service;

import com.example.springdatareactive.example.common.entity.AuthEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : com.example.webfluxserverexample.service
 * fileName : AuthService
 * author : taeil
 * date : 1/31/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/31/24        taeil                   최초생성
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final R2dbcEntityTemplate entityTemplate;
    public Mono<String> getNameByToken(String token) {
        var query = Query.query(
                Criteria.where("token").is(token)
        );

        // 짧게 줄이려면 selectOne으로 그냥 조회해도 된다.
        // entityTemplate.selectOne(query, AuthEntity.class);

        return entityTemplate.select(AuthEntity.class)
                .matching(query)
                .one()
                .map(authEntity -> authEntity.getUserId().toString())
                .doOnNext(name -> log.info("name : {}", name));
    }
}