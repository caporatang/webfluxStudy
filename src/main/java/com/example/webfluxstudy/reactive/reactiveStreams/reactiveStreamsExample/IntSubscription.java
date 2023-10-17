package com.example.webfluxstudy.reactive.reactiveStreams.reactiveStreamsExample;

import lombok.Data;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams
 * fileName : IntSubscription
 * author : taeil
 * date : 2023/10/17
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/17        taeil                   최초생성
 */
@Data
public class IntSubscription implements Flow.Subscription{
    // Flow.Subscription을 구현
    // subscriber의 onNext와 subscription의 request가 동기적으로 동작하면 안되기 때문에 excutor를 이용해서 별도의 쓰레드에서 실행.
    // 요청 횟수를 count에 저장하고 결과에 함께 전달.
    // 더 이상 iterator에 값이 없으면, onComplete 호출

    private final Flow.Subscriber<? super FixedIntPublisher.Result> subscriber;
    private final Iterator<Integer> numbers;
    private final ExecutorService excutor = Executors.newSingleThreadExecutor();
    private final AtomicInteger count = new AtomicInteger(1);
    private final AtomicBoolean isCompleted = new AtomicBoolean(false);


    @Override
    public void request(long n) {
        excutor.submit(() -> {
            for (int i = 0; i < n; i++) {
                if(numbers.hasNext()) {
                    int number = numbers.next();
                    numbers.remove();
                    subscriber.onNext(new FixedIntPublisher.Result(number, count.get()));
                } else {
                    var isChanged = isCompleted.compareAndSet(false, true);
                    if (isChanged) {
                        excutor.shutdown();
                        subscriber.onComplete();
                        isCompleted.set(true);
                    }
                    break;
                }
            }
            count.incrementAndGet();
        });
    }

    @Override
    public void cancel() {
        subscriber.onComplete();
    }
}