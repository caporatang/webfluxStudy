package com.example.webfluxstudy.reactor.connection.delay;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * packageName : com.example.webfluxstudy.reactor.connection
 * fileName : DocsDelayElements
 * author : taeil
 * date : 12/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/27/23        taeil                   최초생성
 */
@Slf4j
public class DelayedElementsExample {
    // delayElements -> 생성된 소스에 붙일 수 있는 연산자
    // 최소 delay만큼의 간격을 두고 onNext 이벤트가 발행된다.
    // onNext 이벤트가 발행된 후 delay보다 더 늦게 다음 onNext 이벤트가 전달되면 '바로' 전파된다.

    // ex)
    // delayElements(1) -> 1초 후에 실행, 1초만큼 딜레이
    @SneakyThrows
    public static void main(String[] args) {
        Flux.create(
                sink -> {
                    for (int i = 0; i <= 5 ; i++) {
                        try {
                            // 100미리 세크 간격으로 sink
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        sink.next(i);
                    }
                    sink.complete();
                })
                // 딜레이는 500이다. -> 100미리 간격으로 하나씩 오는데 500미리 세크로 값 전달이 딜레이.
                .delayElements(Duration.ofMillis(500))
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribeOn(Schedulers.single())
                .subscribe();
        Thread.sleep(6000);
    }
}