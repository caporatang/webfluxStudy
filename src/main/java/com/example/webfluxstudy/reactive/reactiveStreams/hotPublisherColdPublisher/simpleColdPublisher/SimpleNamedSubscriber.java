package com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleColdPublisher;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleColdPublisher
 * fileName : SimpleNamedSubscriber
 * author : taeil
 * date : 2023/10/18
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/18        taeil                   최초생성
 */
@Slf4j
public class SimpleNamedSubscriber<T> implements Flow.Subscriber {
    private Flow.Subscription subscription;
    private final String name;

    public SimpleNamedSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
        log.info("onSubscribe");
    }

    @Override
    public void onNext(Object item) {
        log.info("name: {}, onNext: {}", name, item);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("onError: {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
    }

    public void cancel() {
        log.info("cancel");
        this.subscription.cancel();
    }
}