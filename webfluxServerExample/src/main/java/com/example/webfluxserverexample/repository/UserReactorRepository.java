package com.example.webfluxserverexample.repository;

import com.example.webfluxserverexample.common.entity.UserEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;

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
public class UserReactorRepository {
    private final Map<String, UserEntity> userMap;

    public UserReactorRepository() {
        var user = new UserEntity("1234", "taeil", 29, "1");

        userMap = Map.of("1234", user);
    }

    @SneakyThrows
    public Mono<UserEntity> findById(String userId) {
        return Mono.create(sink -> {
            log.info("UserRepository.findById: {}", userId);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            UserEntity user = userMap.get(userId);
            if (user == null) {
                sink.success();
            } else {
                sink.success(user);
            }
        });
    }
}