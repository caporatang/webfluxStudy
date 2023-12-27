package com.example.webfluxstudy.reactor.connection.merge;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * packageName : com.example.webfluxstudy.reactor.connection.merge
 * fileName : FluxMergeExample
 * author : taeil
 * date : 12/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/27/23        taeil                   최초생성
 */
@Slf4j
public class FluxMergeExample {
    // concat과 다르게 1초씩 반환하는 Flux가 있을때 subscribe 시점이 동일하게 시작되기 때문에 complete 시간은 동일할것임.
    // -> 순차적으로 하나씩 처리되는것보다 훨씬 빠르게 처리 가능!
    @SneakyThrows
    public static void main(String[] args) {
        var flux1 = Flux.range(1, 3)
                .doOnSubscribe(value -> {
                    log.info("doOnSubscribe1");
                })
                .delayElements(Duration.ofMillis(100));

        var flux2 = Flux.range(10, 3)
                .doOnSubscribe(value -> {
                    log.info("doOnSubscribe2");
                })
                .delayElements(Duration.ofMillis(100));

        Flux.merge(flux1, flux2)
                .doOnNext(value -> {
                    log.info("doOnNext : " + value);
                })
                .subscribe();
        Thread.sleep(1000);
    }
}