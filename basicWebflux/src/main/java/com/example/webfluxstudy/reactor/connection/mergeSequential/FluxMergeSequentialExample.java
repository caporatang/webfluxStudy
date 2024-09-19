package com.example.webfluxstudy.reactor.connection.mergeSequential;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * packageName : com.example.webfluxstudy.reactor.connection.mergeSequential
 * fileName : FluxMergeSequentialExample
 * author : taeil
 * date : 12/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/27/23        taeil                   최초생성
 */
@Slf4j
public class FluxMergeSequentialExample {
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

        // merge와 다른 부분은 호출밖에 없음
        Flux.mergeSequential(flux1, flux2)
                .doOnNext(value -> {
                    log.info("doOnNext : " + value);
                })
                .subscribe();
        Thread.sleep(1000);
    }
}
