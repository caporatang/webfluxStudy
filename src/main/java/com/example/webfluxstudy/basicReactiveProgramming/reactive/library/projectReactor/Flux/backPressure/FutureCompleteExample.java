package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.Flux.backPressure;

import com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Flux.backPressure
 * fileName : FutureCompleteExample
 * author : taeil
 * date : 2023/10/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/20        taeil                   최초생성
 */
@Slf4j
public class FutureCompleteExample {
    public static void main(String[] args) {
        log.info("start main");
        getItems().subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Flux<Integer> getItems() {
        return Flux.create(fluxSink -> {
            // request는 했지만 바로 complete를 호출 ->  바로 종료!
            fluxSink.complete();
        });
    }
}