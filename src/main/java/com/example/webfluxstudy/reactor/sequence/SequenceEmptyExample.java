package com.example.webfluxstudy.reactor.sequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * packageName : com.example.webfluxstudy.reactor.sequence
 * fileName : SequenceEmptyExample
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
@Slf4j
public class SequenceEmptyExample {
    // Mono.empty 혹은 Flux.empty를 통해서 subscriber에게 onComplete 이벤트만 전달
    // 단순히 텅 빈 값을 반환하지만, 어떤 사건을 전달하거나 값이 있거나 없거나 둘 중 하나인데, 값이 없다. 를 처리 할 수도있다.
    // '이벤트'도 전달 가능하다 -> 어떤 이벤트가 발생해서 처리가 완료되었다!

    public static void main(String[] args) {
        Mono.empty()
                .subscribe(value -> {
                    log.info("value: " + value);
                }, null, () -> {
                    log.info("complete");
                });

        Flux.empty()
                .subscribe(value -> {
                    log.info("value : " + value);
                }, null, () -> {
                    log.info("complete");
                });
    }

}