package com.example.webfluxstudy.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactor
 * fileName : SubscribeBaseSubscriberExample
 * author : taeil
 * date : 11/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/27/23        taeil                   최초생성
 */
@Slf4j
public class SubscribeBaseSubscriberExample {
    // reactor에서 BaseSubscriber를 제공한다.
    // onNext, onComplete, onError, onSubscribe 를 직접 호출하는 대신,
    // hookOnNext, hookOnComplete, hookOnError, hookOnSubscribe를 구현한다.
    // subscriber 외부에서 request와 cancel을 호출 가능하다.
    // 기본적으로 unbounded request가 적용된다.

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
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .subscribe(subscriber);
        
        // 외부에서 호출!
        // subscriber.request(1);
        // subscriber.cancel();
        log.info("end main");
    }
    // subscribe -  backPressure
    // unbounded request는 Publisher에게 가능한 빠르게 아이템을 전달해달라는 요청이다.
    // request(Long.MAX_VALUE)로 실행
    // backPressure를 비활성화 하는것과 동일한것임.

    // unbounded request가 일어나는 경우
    // 아무것도 넘기지 않는 그리고 lambda기반의 subscribe()
    // BaseSubscriber의 hookOnSubscribe를 그대로 사용.
    // block, blockFirst, blockLast 등의 blocking 연산자
    // tolterable, toStream 등의 toCollect 연산자


}