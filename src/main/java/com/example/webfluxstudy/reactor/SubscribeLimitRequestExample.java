package com.example.webfluxstudy.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

/**
 * packageName : com.example.webfluxstudy.reactor
 * fileName : SubscribeLimitRequestExample
 * author : taeil
 * date : 11/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/28/23        taeil                   최초생성
 */
@Slf4j
public class SubscribeLimitRequestExample {
    // take(n, limitRequest)
    // subscriber 외부에서 연산자를 통해서 최대 개수를 제한
    // limitRequest가 true인 경우 정확히 n개만큼 요청 후 complete 이벤트를 전달
    // BaseSUbscriber의 기본 전략이 unbounded request 이지만 take(5, true) 로 인해서 5개 전달 후 complete 이벤트가 발생된다.

    public static void main(String[] args) {
        log.info("start main");

        var subscriber = new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnNext(Integer value) {
                log.info("value: " + value);
            }

            @Override
            protected void hookOnComplete() {
                log.info("complete");
            }
        };

        Flux.fromStream(IntStream.range(0, 10).boxed())
                .take(5)
                .subscribe(subscriber);

        log.info("end main");
    }
}