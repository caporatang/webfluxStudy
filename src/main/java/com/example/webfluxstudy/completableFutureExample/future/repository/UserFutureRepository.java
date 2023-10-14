package com.example.webfluxstudy.completableFutureExample.future.repository;

import com.example.webfluxstudy.completableFutureExample.common.entity.UserEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.blocking.repository
 * fileName : UserRepository
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Slf4j
public class UserFutureRepository {
    private final Map<String, UserEntity> userMap;

    public UserFutureRepository() {
        var user = new UserEntity("1234", "taeil", 28, "image#1000");

        userMap = Map.of("1234", user);
    }

    @SneakyThrows
    public CompletableFuture<Optional<UserEntity>> findById(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("UserRepository.findById: {}", userId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                var user = userMap.get(userId);
            return Optional.ofNullable(user);
        });
    }
}