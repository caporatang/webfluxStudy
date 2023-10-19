package com.example.webfluxstudy.reactive.library.projectReactor.Flux.backPressure;

import reactor.core.publisher.Flux;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Flux
 * fileName : FluxContinuousRequestSubscriberExample
 * author : taeil
 * date : 2023/10/19
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/19        taeil                   최초생성
 */
public class FluxContinuousRequestSubscriberExample {
    // back pressure 예제

    public static void main(String[] args) {
        getItems().subscribe(new ContinuousRequestSubscriber<>());
    }
    private static Flux<Integer> getItems(){
        return Flux.fromIterable(List.of(1,2,3,4,5));
    }

}