package com.example.webfluxstudy.basicReactiveProgramming.reactive.reactiveStreams.reactiveStreamsExample;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Flow;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams
 * fileName : FixedIntPublisher
 * author : taeil
 * date : 2023/10/17
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/17        taeil                   최초생성
 */
public class FixedIntPublisher implements Flow.Publisher<FixedIntPublisher.Result>{
    // Flow.Publisher를 구현
    // 고정된 숫자의 integer를 전달하는 publisher
    // 8개의 integer를 전달 후 complete -> 유한한 크기의 publisher

    // iterator를 생성해서 subscription을 생성하고 subscriber에게 전달.
    // requestCount를 세기 위해서 Result 객체 사용

    @Data
    public static class Result {
        // -> 몇번째 요청을 한것임을 저장하기 위한 객체
        private final Integer value;
        private final Integer requestCount;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Result> subscriber) {
        var numbers = Collections.synchronizedList(
                new ArrayList<>(List.of(1,2,3,4,5,6,7))
        );

        Iterator<Integer> iterator = numbers.iterator();
        var subscription = new IntSubscription(subscriber, iterator); // subscription을 만들고
        subscriber.onSubscribe(subscription); // subscriber에게 전달
    }
}