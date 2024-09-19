package com.example.webfluxstudy.reactor.usefulOperator.map;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.usefulOperator.map
 * fileName : MapExample
 * author : taeil
 * date : 12/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/28/23        taeil                   최초생성
 */
@Slf4j
public class MapExample {
    // onNext 이벤트를 받아서 값을 변경하고 아래로 전달한다.
    // mapNotNull은 변경된 값이 null인 경우를 필터 한다.

    public static void main(String[] args) {
        Flux.range(1, 5)
                .map(value -> value * 2)
                // value를 *2 한 값을 다음 파이프라인으로.
                .doOnNext(value -> {
                    log.info("doOnNext : " + value);
                })
                .subscribe();

        Flux.range(1, 5)
                .mapNotNull(value -> {
                    // flux에서는 -> publisher에서는 map을 사용하지 않으면 null을 다음으로 넘긴다면, error에 해당하지만 map을 사용하면 가능하다.
                    if (value % 2 == 0) {
                        return value;
                    }
                    return null;
                })
                .doOnNext(value -> {
                    log.info("doOnNext : " + value);
                })
                .subscribe();
    }
}