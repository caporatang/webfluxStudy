package com.example.webfluxstudy.reactor.thread.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler
 * fileName : NewSingleSchedulerExample
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class NewSingleSchedulerExample {
    // single, parallel, boundedElastic 모두 캐싱된 쓰레드풀을 제공한다.

    // 캐싱되어 있는 스레드풀이 아니라 중요한 작업이라 특정 공간 즉, 새로운 공간을 할당해서 작업해야 하는 경우라면 ?
    // newSingle, newParallel, newBoundedElastic 은 새로운 쓰레드풀을 만들어서 제공한다.
    // dispose로 쓰레드풀을 해제할 수 있다.

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i <100; i++) {
            // new 싱글로 스레드풀은 100개가 만들어진다~
            var newSingle = Schedulers.newSingle("single");
            final int idx = i;
            Flux.create(sink -> {
                log.info("next : {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    newSingle
            ).subscribe(value -> {
                log.info("value :" + value);
                newSingle.dispose(); // 자동 해제가 되지 않음
            });
        }
        Thread.sleep(1000);
    }
}