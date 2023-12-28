package com.example.webfluxstudy.reactor.usefulOperator.flatMap;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.usefulOperator.flatMap
 * fileName : FlatMapExample
 * author : taeil
 * date : 12/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/28/23        taeil                   최초생성
 */
@Slf4j
public class FlatMapExample {
    // onNext 이벤트를 받아서 'publisher' 를 반환한다.
    // publisher의 이벤트를 아래로 전달한다.
    // 여러 publisher를 조합해야하는 경우 유용하게 사용된다.

    public static void main(String[] args) {
        Flux.range(1, 5)
                .flatMap(value -> {
                    return Flux.range(1, 2)
                            .map(value2 -> value + ", " + value2)
                            .publishOn(Schedulers.parallel());
                })
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribe();
    }
}