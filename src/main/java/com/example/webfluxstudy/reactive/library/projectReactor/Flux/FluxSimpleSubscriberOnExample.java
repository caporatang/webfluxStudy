package com.example.webfluxstudy.reactive.library.projectReactor.Flux;

import com.example.webfluxstudy.reactive.library.projectReactor.SimpleSubscriber;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Flux
 * fileName : FluxSimpleSubscriberOnExample
 * author : taeil
 * date : 2023/10/19
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/19        taeil                   최초생성
 */
@Slf4j
public class FluxSimpleSubscriberOnExample {
    // main이 아닌 다른 스레드에서 실행하고 싶다면 subscribeOn() 을 사용하자.
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        getItems()
                .map(i -> {
                    log.info("map {}", i);
                    return i;
                })
                .subscribeOn(Schedulers.single()) // 어떠한 스케쥴러를 이용할지 설정 -> singleThread 생성해서 실행하겠다. -> 다양한 스레드 사용 가능
                .subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
        Thread.sleep(1000);
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1,2,3,4,5));
    }
}