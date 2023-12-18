package com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn.publishOn;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn.publishOn
 * fileName : PublishOnSchedulerExample
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class PublishOnSchedulerExample {

    @SneakyThrows
    public static void main(String[] args) {
        // subscribeOn이 없으므로 main에서 동작
        Flux.create(sink -> {
            for (int i = 0; i <5; i++) {
                log.info("next : {}", i);
                sink.next(i);
            }
        }).publishOn(
                // 싱글 쓰레드로 변경!
                Schedulers.single()
        ).doOnNext(item -> {
            // 싱글 스케쥴러에서 동작
            log.info("doOnNext : ", item);
        }).publishOn(
            // boundedElastic 으로 변경!
                Schedulers.boundedElastic()
        ).doOnNext(item -> {
            log.info("doOnNext2 : {}", item);
        }).subscribe(value -> {
            log.info("value: " + value);
        });
        Thread.sleep(1000);
    }
}