package com.example.webfluxstudy.reactor.usefulOperator.takeSkip;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.usefulOperator.takeSkip
 * fileName : SkipExample
 * author : taeil
 * date : 12/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/28/23        taeil                   최초생성
 */
@Slf4j
public class SkipExample {
    // skip : 처음 n개의 onNext 이벤트를 무시하고 그 이후 onNext 이벤트를 전파한다.
    // skipLast : onComplete 이벤트가 발생하기 직전 n개의 onNext 이벤트 무시

    @SneakyThrows
    public static void main(String[] args) {
        Flux.range(1, 10)
                .skip(5)
                .doOnNext(value -> {
                    log.info("not skipped : " + value);
                })
                .subscribe();

        Flux.range(1, 10)
                .skipLast(5)
                .doOnNext(value -> {
                    log.info("not skipped : " + value);
                })
                .subscribe();
        Thread.sleep(1000);
    }
}