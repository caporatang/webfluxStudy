package com.example.webfluxstudy.reactor.sequence;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence
 * fileName : SequenceErrorExample
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
@Slf4j
public class SequenceErrorExample {
    // Mono.error 혹은 Flux.error를 통해서 subscriber에게 onError 이벤트만 전달한다.
    public static void main(String[] args) {
        Mono.error(new RuntimeException("mono error"))
                .subscribe(value -> {
                    log.info("value : " + value);
                }, error -> {
                    // 두번째 인자는 에러
                    log.error("error : " + error);
                });

        Flux.error(new RuntimeException("flux error"))
                .subscribe(value -> {
                    log.info("value : " + value);
                }, error -> {
                    // 두번째 인자는 에러
                    log.error("error : " + error);
                });
    }
}