package com.example.webfluxstudy.basicReactiveProgramming.reactive.reactiveStreams.hotPublisherColdPublisher.simpleColdPublisher;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleColdPublisher
 * fileName : SimpleColdPublisher
 * author : taeil
 * date : 2023/10/18
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/18        taeil                   최초생성
 */
public class SimpleColdPublisher implements Flow.Publisher<Integer>{
    // Subscriber 하는 순간부터 데이터를 내려주는 퍼블리셔. -> 동일한 데이터가 보장되는 경우
    // ex) 파일 읽어오기

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        var iterator = Collections.synchronizedList(
                IntStream.range(1, 10).boxed().collect(Collectors.toList()) // 생성한 값. -> subscribe 하게 된다면 출력될 값 -> cold publisher
        ).iterator();
        var subscription = new SimpleColdSubscription(iterator, subscriber);
        subscriber.onSubscribe(subscription);
    }

    @RequiredArgsConstructor
    public class SimpleColdSubscription implements Flow.Subscription {
        private final Iterator<Integer> iterator;
        private final Flow.Subscriber<? super Integer> subscriber;
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();

        @Override
        public void request(long n) {
            // 새로운 스레드에서 요청 받은 만큼 값을 넘기는 형태
            executorService.submit(() -> {
                for (int i = 0; i < n; i++) {
                    if(iterator.hasNext()) {
                        var number = iterator.next();
                        iterator.remove();
                        subscriber.onNext(number);
                    } else {
                        // iterator 값이 없어지면 shutdown
                        subscriber.onComplete();
                        executorService.shutdown();
                        break;
                    }

                }
            });
        }

        @Override
        public void cancel() {
            subscriber.onComplete();
        }
    }
}