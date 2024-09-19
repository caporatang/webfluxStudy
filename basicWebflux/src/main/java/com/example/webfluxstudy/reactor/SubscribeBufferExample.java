package com.example.webfluxstudy.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

/**
 * packageName : com.example.webfluxstudy.reactor
 * fileName : SubscribeBufferExample
 * author : taeil
 * date : 11/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/28/23        taeil                   최초생성
 */
@Slf4j
public class SubscribeBufferExample {
    // buffer 연산자도 제공된다!
    // buffer(N) 호출시 N개만큼 모아서 List로 전달한다.
    // buffer(3) 호출 후 request(2) 를 하는 경우, 3개가 담긴 List 2개가 Subscriber에게 전달된다.
    // -> 즉, 6개의 item이 전달된다.

    public static void main(String[] args) {
        log.info("start main");

        var subscriber = new BaseSubscriber<List<Integer>>() {
            private Integer count = 0;
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(2);
            }
            @Override
            protected void hookOnNext(List<Integer> value) {
                log.info("value: " + value);
                if (++count == 2) cancel();
            }
            @Override
            protected void hookOnComplete() {
                log.info("complete");
            }
        };

        // onNext를 사용해서 하나씩 받는것이 아니라 buffer를 사용해서 모아서 받을 수 있음
        // buffer(n) : n * request count
        Flux.fromStream(IntStream.range(0, 10).boxed())
                .buffer(3)
                .subscribe(subscriber);

        log.info("end main");
    }
}