package com.example.webfluxstudy.reactor.connection.concat;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * packageName : com.example.webfluxstudy.reactor.connection.concat
 * fileName : FluxConcatExample
 * author : taeil
 * date : 12/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/27/23        taeil                   최초생성
 */
@Slf4j
public class FluxConcatExample {
    // concat을 사용하면 순서를 보장하고 publisher를 합칠수 있다.
    // 하나씩 순차적으로 진행이 되기 때문에, 1초가 걸리는 Flux의 요소가 5개라면 5초가 걸린다 -> 성능에 문제가 있을 수 있음
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

        Flux.concat(flux1, flux2)
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribe();

        Thread.sleep(1000);
    }
}