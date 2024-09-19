package com.example.webfluxstudy.reactor.thread.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler
 * fileName : ParallelSchedulerExample
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class ParallelSchedulerExample {
    // 캐싱된 n개 크기의 쓰레드풀을 제공한다.
    // 기본적으로 CPU 코어 수만큼의 크기를 갖는다.

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            Flux.create(sink -> {
                log.info("next : {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    // Single과 호출하는 스케쥴러만 다르다.
                    // 실행 결과를 보면 실행하는 컴퓨터의 코어 갯수만큼 쓰레드가 실행된다
                    Schedulers.parallel()
            ).subscribe(value -> {
                log.info("value :" + value);
            });
        }
        Thread.sleep(2000);
    }
}