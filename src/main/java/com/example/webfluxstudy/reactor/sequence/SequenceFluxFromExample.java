package com.example.webfluxstudy.reactor.sequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence
 * fileName : SequenceFluxFromExample
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
@Slf4j
public class SequenceFluxFromExample {
    // flux from : 여러개의 값을 받아서 그 값들을 next로 전달한다.

    public static void main(String[] args) {
        Flux.fromIterable(
        // fromIterable : Iterable을 받아서 각각의 item을 onNext로 전달
                List.of(1, 2, 3, 4, 5)
        ).subscribe(value -> {
            log.info("value : " + value);
        });

        Flux.fromStream(
        // fromStream : Stream을 받아서 각각의 item을 onNext로 전달
                IntStream.range(1, 6).boxed()
        ).subscribe(value -> {
            log.info("value : " + value);
        });

        Flux.fromArray(
        // fromArray : Array를 받아서 각각의 itemdmf OnNext로 전달
                new Integer[]{1,2,3,4,5}
        ).subscribe(value -> {
            log.info("value: " + value);
        });

        Flux.range(
        // range(start, n)
        // from의 연산자는 아니지만 유용하게 사용가능하고, 비슷한 역할을 할 수 있다
        // start부터 시작해서 한개씩 커진 값을 n개만큼 onNext로 전달
                1,5
        ).subscribe(value -> {
            log.info("value : " + value);
        });

    }
}