package com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.blocking.repository
 * fileName : FollowRepository
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Slf4j
public class FollowRepository {
    private Map<String, Long> userFollowCountMap;

    public FollowRepository() {
        userFollowCountMap = Map.of("1234", 1000L);
    }

    @SneakyThrows
    public Long countByUserId(String userId) {
        log.info("FollowRepository.countByUserId: {}", userId);
        Thread.sleep(1000);
        return userFollowCountMap.getOrDefault(userId, 0L);
    }
}