package com.example.webfluxstudy.reactor.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.reactor.context
 * fileName : ContextLogger
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
@Slf4j
public class ContextLogger {
    public static <T> Mono<T> logContext(T t, String name) {
        return Mono.deferContextual(c -> {
            log.info("name: {}, context: {}", name, c);
            return Mono.just(t);
        });
    }
}