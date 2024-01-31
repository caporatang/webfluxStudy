package com.example.webfluxserverexample.service;

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
@Service
public class AuthService {
    private static final Map<String, String> tokenUserIdMap = Map.of("abcd", "1234");

    public Mono<String> getNameByToken(String token) {
        // 주어진 토큰에 대해서 ID 받고 반환
        return Mono.justOrEmpty(tokenUserIdMap.get(token));
    }
}