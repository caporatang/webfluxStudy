package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.Mono;

import com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Mono
 * fileName : FluxToMonoExample
 * author : taeil
 * date : 2023/10/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/20        taeil                   최초생성
 */
@Slf4j
public class FluxToMonoExample {
    // Flux를 Mono로 변환
    // Mono.from으로 Flux를 Mono로
    // 첫 번째 값만 전달한다.

    public static void main(String[] args) {
        log.info("start main");
        Mono.from(getItems()).subscribe(
                // 다른 퍼블리셔를 넣으면 해당 퍼블리셔의 next값을 받아서 next가 온다면 바로 success를 실행시킴 (complete)
                new SimpleSubscriber<>(Integer.MAX_VALUE)
        );
        log.info("end main");
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1,2,3,4,5));
    }
}