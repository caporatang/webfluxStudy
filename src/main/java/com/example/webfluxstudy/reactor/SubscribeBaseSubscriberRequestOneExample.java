package com.example.webfluxstudy.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactor
 * fileName : SubscribeBaseSubscriberRequestOneExample
 * author : taeil
 * date : 11/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/27/23        taeil                   최초생성
 */
@Slf4j
public class SubscribeBaseSubscriberRequestOneExample {
    // hookOnSubscribe 를 orerride 하여 1개만 request 한다.
    // onNext 이벤트가 발생하면 cancel을 실행한다.

    public static void main(String[] args) {
        var subscriber = new BaseSubscriber<Integer>(){
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                // 하나를 요청
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                // 요청
                log.info("value : {}", value );
                // 하나 요청하고 바로 캔슬
                cancel();
            }

            @Override
            protected void hookOnComplete() {
                log.info("complete");
            }
        };

        Flux.fromIterable(List.of(1,2,3,4,5))
                .subscribe(subscriber);
    }
}