package com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn
 * fileName : PublishOnSubscribeOnExample
 * author : taeil
 * date : 12/19/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/19/23        taeil                   최초생성
 */
@Slf4j
public class PublishOnSubscribeOnExample {
    // publishOn 과 subscribeOn을 섞어 쓰면?

    @SneakyThrows
    public static void main(String[] args) {
        Flux.create(sink -> {
            //  subscribeOn 은 위치에 상관없이 동작한다 -> parallel 스케쥴려에서 실행된다
            for (int i = 0; i <5; i++) {
                log.info("next : {}", i);
                sink.next(i);
            }
        }).publishOn(
                // 싱글로 변경
                Schedulers.single()
        ).doOnNext(item -> {
            // 싱글스레드에서 동작
            log.info("doOnNext : {}", item);
        }).publishOn(
                // boundedElastic 으로 변경
                Schedulers.boundedElastic()
        ).doOnNext(item -> {
            // 여기부터 쭉 아래로 boundedElastic 쓰레드에서 동작
            log.info("doOnNext2: {}", item);
        }).subscribeOn(Schedulers.parallel()
        ).subscribe(value -> {
            log.info("value : " + value);
        });
        Thread.sleep(1000);
    }
}