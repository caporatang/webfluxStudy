package com.example.webfluxstudy.reactive.reactiveStreams.reactiveStreamsExample;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams.reactiveStreamsExample
 * fileName : RequestNSubscriber
 * author : taeil
 * date : 2023/10/17
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/17        taeil                   최초생성
 */
@Data
@Slf4j
public class RequestNSubscriber<T> implements Flow.Subscriber {
    // Flow.Subscriber를 구현
    // 최초 연결시 1개를 고정적으로 요청한다.
    // onNext에서 count를 세고 n번째 onNext 마다 request
    // onNext, onComplete, onError를ㄹㄹ 받으면 로그 남김

    private final Integer n;
    private Flow.Subscription subscription;
    private int count = 0;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1); // 최초 연결시 1개를 고정적으로 요청한다.
    }

    @Override
    public void onNext(Object item) {
      log.info("item: {}", item);
      if (count++ % n == 0) {
          log.info("send request");
          this.subscription.request(n);
      }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error : {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("complete");
    }
}