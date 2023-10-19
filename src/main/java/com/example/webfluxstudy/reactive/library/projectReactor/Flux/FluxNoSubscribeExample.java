package com.example.webfluxstudy.reactive.library.projectReactor.Flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Flux
 * fileName : FluxNoSubscribeExample
 * author : taeil
 * date : 2023/10/19
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/19        taeil                   최초생성
 */
@Slf4j
public class FluxNoSubscribeExample {
    // CompletableFuture의 문제점은 CompletableFuture를 반환하는 함수를 호출하는 순간, 바로 지연 로딩이 되지 않는다는 문제가 있다.
    // reactive stremas 또한 subscribe 하지 않으면, 아무 일도 일어나지 않는다.

    // publisher를 만드는것은 pipeline을 만드는거지, 따로 밀어넣어서 처리하는것이 아님.

    public static void main(String[] args) {
        log.info("start main");
        // flux를 반환은 받지만, subscribe는 하지 않음 -> 아무 동작도 되지 않음
        getItems();
        log.info("end main");
    }

    private static Flux<Integer> getItems() {
        return Flux.create(fluxSink -> {
            log.info("start getItems");
            for (int i = 0; i <5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
            log.info("end getItems");
        });
    }


}