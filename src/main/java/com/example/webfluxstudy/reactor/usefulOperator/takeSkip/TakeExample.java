package com.example.webfluxstudy.reactor.usefulOperator.takeSkip;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.usefulOperator.take
 * fileName : TakeExample
 * author : taeil
 * date : 12/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/28/23        taeil                   최초생성
 */
@Slf4j
public class TakeExample {
    // take : 대략 n개까지 onNext 이벤트 전파하고 n 개에 도달하면 onComplete 이벤트가 발생한다.
    // takeLast : onComplete 이벤트가 발생하기 직전 n개의 아이템만 전파한다.

    @SneakyThrows
    public static void main(String[] args) {
        Flux.range(1, 10)
                .take(5)
                .doOnNext(value -> {
                    log.info("take : " + value);
                })
                .subscribe();

        Flux.range(1, 10)
                .takeLast(5)
                .doOnNext(value -> {
                    log.info("takeLast: " + value);
                })
                .subscribe();
        Thread.sleep(1000);
    }
}