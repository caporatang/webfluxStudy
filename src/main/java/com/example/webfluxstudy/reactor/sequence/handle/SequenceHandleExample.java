package com.example.webfluxstudy.reactor.sequence.handle;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence.handle
 * fileName : SequenceHandleExample
 * author : taeil
 * date : 12/7/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/7/23        taeil                   최초생성
 */
@Slf4j
public class SequenceHandleExample {
    // handle 을 통해서  value와 sink가 전달된다.
    // sink의 next를 호출하여 값을 필터하거나 변경할 수 있고 complete, error를 더 일찍 전달 가능하다.

    public static void main(String[] args) {
        Flux.fromStream(IntStream.range(0, 10).boxed())
                .handle((value, sink) ->{
                    if (value % 2 == 0) {
                        // 짝수일때만 값을 넘겨주자
                        sink.next(value);
                    }
                }).subscribe(value -> {
                    log.info("value : " + value);
                }, error -> {
                    log.error("error: " + error);
                }, () -> {
                    log.info("complete");
                });

    }
}