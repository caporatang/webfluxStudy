package com.example.webfluxstudy.reactor.thread;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.Executors;

/**
 * packageName : com.example.webfluxstudy.reactor.thread
 * fileName : Thread
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class Thread {
    // 아무런 설정을 하지 않았다면, publisher는 subscribe를 호출한 caller의 쓰레드에서 실행된다.
    // subscribe에 제공된 lambda 혹은 Scheduler 또한 caller의 쓰레드에서 실행된다.

    public static void main(String[] args) {
        var executor = Executors.newSingleThreadExecutor();

        try {
            /* single Thread executor 의 공간 -> publisher도 singleThread에서 실행되기 때문에 실행되는 스레드는 하나임 */
            executor.submit(() -> {
                Flux.create(sink -> {
                    for (int i = 0; i <= 5; i++) {
                        log.info("next : {}", i);
                        sink.next(i);
                    }
                }).subscribe(value -> {
                    log.info("value : "+ value);
                });
            });
            /*                                */
        } finally {
            executor.shutdown();
        }
    }
}