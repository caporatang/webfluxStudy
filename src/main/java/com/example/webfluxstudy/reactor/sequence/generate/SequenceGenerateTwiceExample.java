package com.example.webfluxstudy.reactor.sequence.generate;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence.generate
 * fileName : SequenceGenerateExample
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
@Slf4j
public class SequenceGenerateTwiceExample {
    // 한 번의 generator 에서 최대 한 번만 next 호출이 가능함
    // 근데 두 번 호출하면 어떻게 될까 -> 에러 발생!
    // ERROR com.example.webfluxstudy.reactor.sequence.generate.SequenceGenerateTwiceExample - error : java.lang.IllegalStateException: More than one call to onNext
    public static void main(String[] args) {
        Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next(state);
                    sink.next(state);
                    if (state == 9) {
                        sink.complete();
                    }
                    return state + 1;
                }
        ).subscribe(value -> {
            log.info("value : " + value);
        }, error -> {
            log.error("error : " + error);
        }, () -> {
            log.info("complete");
        });
    }
}