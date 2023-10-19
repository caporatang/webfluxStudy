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

        // 본인이 처리 가능한 만큼만 요청
        // -> subscriber에서 감당하기 힘든 양이 올 일이 없고 publisher 에서 많은 데이터를 가지고 있어도 publisher의 데이터 양과 상관없이 처리한다.
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