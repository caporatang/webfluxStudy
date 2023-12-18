package com.example.webfluxstudy.reactor.thread.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler
 * fileName : BoundedElasticExample
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class BoundedElasticExample {
    // 캐싱된 고정되지 않은 크기의 쓰레드풀을 제공한다. -> 여러개의 Flux에서 호출한다면 캐싱된 쓰레드풀이 제공된다, 이 쓰레드풀의 크기는 변경된다
    // 재사용할 수 있는 쓰레드가 있다면 사용하고, 없으면 새로 생성한다.
    // 특정 시간 (기본 60초) 사용하지 않으면 제거한다.
    // 생성 가능한 쓰레드 수(기본 cpu x 10)
    // I/O blocking 작업을 수행할때 적합하다

    // -> 동적으로 스레드를 관리하고, 쓰레드의 갯수가 무제한으로 늘어나지 않는 안전장치를 가지고 있음

    @SneakyThrows
    public static void main(String[] args) {
        // 200개의 Flux에서 동시에 캐시되어 있는 하나의 쓰레드풀을 바라보는 예제코드임.
        for (int i = 0; i <200; i++) {
            final int idx = i;
            Flux.create(sink -> {
                log.info("next : {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    Schedulers.boundedElastic()
            ).subscribe(value -> {
                log.info("value : " + value);
            });
        }
        Thread.sleep(1000);
    }
}