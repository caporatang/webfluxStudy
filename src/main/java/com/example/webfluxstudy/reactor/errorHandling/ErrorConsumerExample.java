package com.example.webfluxstudy.reactor.errorHandling;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling
 * fileName : ErrorConsumerExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class ErrorConsumerExample {
    // 가장 쉽게 error handling할 수 있는 방법.
    // subscribe의 두 번째 인자인 errorConsumer를 통해서 error를 얻고 action을 수행 가능하다.

    public static void main(String[] args) {
        Flux.error(new RuntimeException("error"))
                .subscribe(value -> {
                    log.info("value : " + value);
                }, error -> {
                    // error consumer를 제공하는 부분
                    log.info("error : " + error);
                });
    }
}