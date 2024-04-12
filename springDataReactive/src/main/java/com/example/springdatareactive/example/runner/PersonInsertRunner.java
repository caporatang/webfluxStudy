package com.example.springdatareactive.example.runner;

import com.example.springdatareactive.example.common.entity.UserEntity;
import com.example.springdatareactive.example.repository.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * packageName : com.example.springdatareactive.example.runner
 * fileName : PersonInsertRunner
 * author : taeil
 * date : 4/12/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/12/24        taeil                   최초생성
 */
@Slf4j
// @Component
@RequiredArgsConstructor
public class PersonInsertRunner implements CommandLineRunner {
    // R2dbc 연동 테스트 insert runner
    private final UserR2dbcRepository userR2dbcRepository;

    @Override
    public void run(String... args) throws Exception {
        var newUser = new UserEntity(null, "taeil", 20, "1", "1q2w3e4r!");
        var saveUser = userR2dbcRepository.save(newUser).block();
        log.info("savedUser : {}", saveUser);

    }
}