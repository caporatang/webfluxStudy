package com.example.webfluxstudy.basicReactiveProgramming.reactive.reactiveStreams.reactiveStreamsExample;

import java.util.concurrent.Flow;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams.reactiveStreamsExample
 * fileName : PublisherSubscriber
 * author : taeil
 * date : 2023/10/17
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/17        taeil                   최초생성
 */
public class PublisherSubscriber {
    // Publisher와 SUbscriber를 생성하고 publisher에 subscriber를 subscribe

    public static void main(String[] args) throws InterruptedException {
        Flow.Publisher publisher = new FixedIntPublisher();
        Flow.Subscriber subscriber = new RequestNSubscriber(Integer.MAX_VALUE);
        //Flow.Subscriber subscriber = new RequestNSubscriber(3);
        //Flow.Subscriber subscriber = new RequestNSubscriber(1);
        publisher.subscribe(subscriber);

        Thread.sleep(100);

        // n이 1일때
        // 1개 처리하고 1개 요청을 complete 할 때까지 반복한다.
        // requestCount가 1개씩 증가한다.

        // n이 3일때
        // 1개씩 처리한 후 3개를 요청
        // 3개 처리하고 다시 3개 요청을 complete까지 반복한다.

        // publisher : 이벤트를 제공한다.
        // subscriber : subscription을 이용해서 값을 처리한다.


    }

}