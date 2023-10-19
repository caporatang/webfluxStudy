package com.example.webfluxstudy.reactive.library.projectReactor.Flux.backPressure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.logging.LogLevel;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Flux.backPressure
 * fileName : ContinuousRequestSubscriber
 * author : taeil
 * date : 2023/10/19
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/19        taeil                   최초생성
 */
@Slf4j
public class ContinuousRequestSubscriber<T> implements Subscriber<T> {
    private final Integer count = 1;
    private Subscription subscription = null;

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription =s;
        log.info("subscribe");
        s.request(count); // count 갯수만큼 요청
        log.info("request: {}", count);
    }

    @SneakyThrows
    @Override
    public void onNext(T t) {
        // 하나 로그 찍고 1초기 다리고 처리 , 반복
        log.info("item: {}", t);

        Thread.sleep(1000);

        subscription.request(1);

        log.info("request:{}", t);

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}