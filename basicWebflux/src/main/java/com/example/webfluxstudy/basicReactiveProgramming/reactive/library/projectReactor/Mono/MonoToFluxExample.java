package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.Mono;

import com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Mono
 * fileName : MonoToFluxExample
 * author : taeil
 * date : 2023/10/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/20        taeil                   최초생성
 */
@Slf4j
public class MonoToFluxExample {
    // flux()
    // Mono를 next 한 번 호출하고 onComplete를 호출하는 Flux로 변환한다.

    public static void main(String[] args) {
        log.info("start main");
        getItems().flux()
                .subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Mono<List<Integer>> getItems() {
        return Mono.just(List.of(1,2,3,4,5));
    }
}