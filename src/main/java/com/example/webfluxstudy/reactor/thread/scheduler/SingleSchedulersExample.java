package com.example.webfluxstudy.reactor.thread.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler
 * fileName : SingleSchedulersExample
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class SingleSchedulersExample {
    // 캐싱된 1개 크기의 쓰레드풀을 제공한다. -> 스케쥴러 시점 싱글을 여러개의 FLUX에서 호춣 하더라도, 스케쥴러는 캐싱을 해놨기 때문에 기존에 만들어놨던 싱글 스케쥴러를 계속해서 제공한다.
    // -> 10000개의 Flux를 요청하더라도 싱글 스레드로 동작할 수 있음

    // 모든 publish, subscribe가 하나의 쓰레드에서 실행된다.

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            Flux.create(sink -> {
                log.info("next : {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    Schedulers.single()
            ).subscribe(value -> {
                log.info("value : " + value);
            });
            Thread.sleep(1000);
        }
    }


}