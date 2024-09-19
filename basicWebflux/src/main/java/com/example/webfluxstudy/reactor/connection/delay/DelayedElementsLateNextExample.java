package com.example.webfluxstudy.reactor.connection.delay;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * packageName : com.example.webfluxstudy.reactor.connection.delay
 * fileName : DelayedElementsLateNextExample
 * author : taeil
 * date : 12/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/27/23        taeil                   최초생성
 */
@Slf4j
public class DelayedElementsLateNextExample {
    // onNext가 더 늦게 발행되는 경우
    // 딜레이보다 더 느리게 doOnNext가 발행된다면 바로바로 OnNext 이벤트가 발행된다.
    @SneakyThrows
    public static void main(String[] args) {
        Flux.create(
                sink -> {
                    for (int i = 0; i <= 5; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        sink.next(i);
                    }
                    sink.complete();
                })
                .delayElements(Duration.ofMillis(500))
                .doOnNext(value -> {
                    log.info("doOnNext : " + value);
                })
                .subscribeOn(Schedulers.single())
                .subscribe();
        Thread.sleep(6000);
    }
}