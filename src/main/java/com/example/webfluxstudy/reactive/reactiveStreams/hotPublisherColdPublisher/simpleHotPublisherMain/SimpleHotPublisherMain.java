package com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleHotPublisherMain;

import com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.SimpleNamedSubscriber;
import lombok.SneakyThrows;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleHotPublisherMain
 * fileName : SimpleHotPublisherMain
 * author : taeil
 * date : 2023/10/18
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/18        taeil                   최초생성
 */
public class SimpleHotPublisherMain {

    @SneakyThrows
    public static void main(String[] args) {
        // prepare publisher
        var publisher = new SimpleHotPublisher();

        // prepare subscriber1
        var subscriber = new SimpleNamedSubscriber<>("subscriber1"); // subscribe 하는 동안 값이 출력 될거고,
        publisher.subscribe(subscriber);


        // cancel after 5s
        Thread.sleep(5000);
        subscriber.cancel(); // 종료 시키고 나면,

        // prepare subscriber 2, 3
        // 처음부터 시작하고 조회하는게 아니라 1에서 하던 작업에 이어서 조회하고 출력하게 될것
        var subscriber2 = new SimpleNamedSubscriber<>("subscriber2");
        var subscriber3 = new SimpleNamedSubscriber<>("subscriber3");
        publisher.subscribe(subscriber2);
        publisher.subscribe(subscriber3);

        // cancel after 5s
        Thread.sleep(5000); // 5초가 딜레이 되면서 1초동안 데이터가 더 생성되었을거고 그 사이에는 공백이 있을것
        subscriber2.cancel();
        subscriber3.cancel();

        Thread.sleep(1000);

        var subscriber4 = new SimpleNamedSubscriber<>("subscriber4");
        publisher.subscribe(subscriber4);

        //cancel after 5s
        Thread.sleep(5000);
        subscriber4.cancel();

        // shutdown publisher
        publisher.shutdown();
    }
}