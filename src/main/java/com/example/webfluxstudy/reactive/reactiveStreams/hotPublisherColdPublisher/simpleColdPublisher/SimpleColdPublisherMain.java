package com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleColdPublisher;

import lombok.SneakyThrows;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleColdPublisher
 * fileName : SimpleColdPublisherMain
 * author : taeil
 * date : 2023/10/18
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/18        taeil                   최초생성
 */
public class SimpleColdPublisherMain {

    @SneakyThrows
    public static void main(String[] args) {
        // create publisher
        var publisher = new SimpleColdPublisher();

        // create subscriber1
        var subscriber = new SimpleNamedSubscriber<Integer>("subscriber1");
        publisher.subscribe(subscriber);

        Thread.sleep(5000);

        // create subscriber2
        var subscriber2 = new SimpleNamedSubscriber<Integer>("subscriber2");
        publisher.subscribe(subscriber2);
    }

}