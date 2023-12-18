package com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn.subscribeOn;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn.subscribeOn
 * fileName : SubscribeOnSchedulerExample
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class SubscribeOnSchedulerExample {

    @SneakyThrows
    public static void main(String[] args) {
        // subscribeOn이 아래에 있어도, 소스에 영향을 주기 때문에
        // 아래에 있는 for문은 boundedElastic 쓰레드에서 동작한다.
        Flux.create(sink -> {
            for (int i = 0; i <5; i++) {
                log.info("next : {}", i);
                sink.next(i);
            }
        }).doOnNext(item -> {
            log.info("doOnNext : {}", item);
        }).doOnNext(item -> {
            log.info("doOnNext2 : {}", item);
        }).subscribeOn(Schedulers.boundedElastic()
        ).subscribe(value -> {
            log.info("value : " + value);
        });
        Thread.sleep(1000);
    }
}