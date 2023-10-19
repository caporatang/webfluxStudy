package com.example.webfluxstudy.reactive.library.projectReactor.Flux.backPressure;

import com.example.webfluxstudy.reactive.library.projectReactor.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Flux.backPressure
 * fileName : FutureErrorExample
 * author : taeil
 * date : 2023/10/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/20        taeil                   최초생성
 */
@Slf4j
public class FutureErrorExample {
    public static void main(String[] args) {
        log.info("start main");
        getItems().subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Flux<Integer> getItems() {
        return Flux.create(fluxSink -> {
            fluxSink.next(0); /// 1개 내림
            fluxSink.next(1); // 2개 내림
            var error = new RuntimeException("error Flux !"); // 에러 전달 : 에러를 전달하고 나면 더 이상 진행되지 않는다.
            fluxSink.error(error);
        });
    }
}