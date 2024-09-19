package com.example.webfluxstudy.reactor.sequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence
 * fileName : SequenceJustExample
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
@Slf4j
public class SequenceJustExample {
    // Mono.just 혹은 Flux.just를 통해서 주어진 객체를 subscriber에게 전달
    public static void main(String[] args) {
        Mono.just(1)
                // 1이라는 숫자를 넣어서 시퀀스 생성
                .subscribe(value -> {
                    log.info("value : " + value);
                });

        Flux.just(1,2,3,4,5)
                // 여러개의 값을 기준으로 시퀀스를 만들 수 있음
                .subscribe(value -> {
                    log.info("value : " + value);
                });
    }
}