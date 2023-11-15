package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.Flux.backPressure;

import com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Flux
 * fileName : FluxSimpleRequestThreeExample
 * author : taeil
 * date : 2023/10/19
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/19        taeil                   최초생성
 */
@Slf4j
public class FluxSimpleRequestThreeExample {
    // backPressure
    // three request -> item : 3
    public static void main(String[] args) {
        getItems().subscribe(new SimpleSubscriber<>(3));
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1,2,3,4,5));
    }
}