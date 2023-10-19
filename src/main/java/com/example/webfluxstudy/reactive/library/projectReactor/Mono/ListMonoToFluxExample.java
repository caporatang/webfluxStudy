package com.example.webfluxstudy.reactive.library.projectReactor.Mono;

import com.example.webfluxstudy.reactive.library.projectReactor.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Mono
 * fileName : ListMonoToFluxExample
 * author : taeil
 * date : 2023/10/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/20        taeil                   최초생성
 */
@Slf4j
public class ListMonoToFluxExample {
    // flatMapMany()
    // Mono의 값으로 여러 개의 값을 전달하는 Flux를 만들고 연결한다.
    // Flux의 내부값으로 Flux를 만들어서 리턴한다

    public static void main(String[] args) {
        log.info("start main");
        getItems()
                .flatMapMany(value -> Flux.fromIterable(value))
                .subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static reactor.core.publisher.Mono<List<Integer>> getItems() {
        return Mono.just(List.of(1,2,3,4,5));
    }
}