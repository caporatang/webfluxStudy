package com.example.webfluxstudy.reactive.library.projectReactor.Flux;

import com.example.webfluxstudy.reactive.library.projectReactor.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Flux
 * fileName : FluxSimpleExample
 * author : taeil
 * date : 2023/10/19
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/19        taeil                   최초생성
 */
@Slf4j
public class FluxSimpleExample {
    public static void main(String[] args) {
        // 해당 예제는 전부 main 스레드에서 실행된다.
        log.info("start main");
        getItems().subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE)); // maxValue 만큼 요청
        log.info("end main");
    }
    private static Flux<Integer> getItems() {
        // 1, 2, 3, 4, 5 를 가지고 Flux 객체 생성 -> FixedIntPublisher와 동일하다 (고정된 값을 가지고 Subscriber에게 요청)
        return Flux.fromIterable(List.of(1,2,3,4,5));
    }
}