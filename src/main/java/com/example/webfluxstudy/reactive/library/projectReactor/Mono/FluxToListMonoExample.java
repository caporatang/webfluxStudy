package com.example.webfluxstudy.reactive.library.projectReactor.Mono;

import com.example.webfluxstudy.reactive.library.projectReactor.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Mono
 * fileName : FluxToListMonoExample
 * author : taeil
 * date : 2023/10/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/20        taeil                   최초생성
 */
@Slf4j
public class FluxToListMonoExample {
    // collectList()
    // Flux의 값들을 collect하고 complete 이벤트가 발생하는 시점에 모은 값들을 전달한다.

    public static void main(String[] args) {
        log.info("start main");
        getItems()
                .collectList() // -> subscriber이자 publisher
                .subscribe(
                        new SimpleSubscriber<>(Integer.MAX_VALUE)
                );
        log.info("end main");
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1,2,3,4,5));
        // 1,2,3,4,5 라는 값들을 받아서 내부 배열 형태로 저장한다
        // onComplete가 발생하는 순간 가지고 있던 값을  그대로 onNext로 넘겨줌
        // 1,2,3,4,5 라는 배열이 Mono로 넘어가게 된다.
    }
}