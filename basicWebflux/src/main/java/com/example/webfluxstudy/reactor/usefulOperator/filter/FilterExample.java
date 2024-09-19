package com.example.webfluxstudy.reactor.usefulOperator.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.usefulOperator.filter
 * fileName : FilterExample
 * author : taeil
 * date : 12/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/28/23        taeil                   최초생성
 */
@Slf4j
public class FilterExample {
    // onNext 이벤트를 받아서 boolean을 반환한다.
    // true 라면 onNext 이벤트를 전파하고 false라면 필터한다.

    public static void main(String[] args) {
        Flux.range(1, 5)
                .filter(value -> value % 2 == 0)
                .doOnNext(value -> {
                    log.info("doOnNext : " + value);
                })
                .subscribe();
    }
}