package com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleHotPublisherMain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Future;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher.simpleHotPublisherMain
 * fileName : SimpleHotPublisher
 * author : taeil
 * date : 2023/10/18
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/18        taeil                   최초생성
 */
@Slf4j
public class SimpleHotPublisher implements Flow.Publisher<Integer>{
    // 누군가가 구독을 하지 않는다고 하더라도 값은 계속 생성되고 있어야한다.
    // ex) SNS를 생각해보면 사용자가 접속을 하지 않는다고 SNS에 새로운 글이나 뭔가 활동이 없는것이 아니다.

    private final ExecutorService publisherExecutor = Executors.newSingleThreadExecutor();
    private final Future<Void> task;
    private static List<Integer> numbers = new ArrayList<>();
    private static List<SimpleHotSubscription> subscriptions = new ArrayList<>();

    public SimpleHotPublisher() {
        // SimpleHotPublisher가 생성되는 순간 계속해서 값이 생성
        numbers.add(1);
        task = publisherExecutor.submit(() -> {
            for (int i = 2;!Thread.interrupted(); i++) {
                numbers.add(i);
                // 새로운 값이 생성되면 전달.
                subscriptions.forEach(SimpleHotSubscription::wakeUp);

                Thread.sleep(100);
            }
           return null;
        });
    }

    public void shutdown() {
        this.task.cancel(true);
        publisherExecutor.shutdown();
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        // 새로운 값이 생겼다. -> subscriber 들에게 새로운 값이 생성 되었음을 전파해야함.
        // 전파할때, subscriber들에게 요청한 값이 있는지 확인하고 줄수있는 상황이면 바로 줘버리고, 만약 따로 request가 오지 않았다면 새로운 값이 추가 되었음을 알고만 있어.. 라는 설정이 필요함

        // subscriber에게 줄 subscription 생성
        var subscription = new SimpleHotSubscription(subscriber);
        subscriber.onSubscribe(subscription);
        subscriptions.add(subscription);
    }

    private static class SimpleHotSubscription implements Flow.Subscription {
        // HotPublisher의 특징은 subscriber한 순간부터 데이터를 받아간다는 특징이 있음 -> 쌓이고 있는 중간에 subscribe 한다면 쌓이는 데이터 중간부터 값을 받아감
        // -> subscription이 최초에 생성되었을 때는, numbers(publisher가 계속 쌓고있는 데이터) 의 마지막 값을 가르켜야 한다.
        // 각각의 subscriber는 개인마다 offset이 따로 존재 해야 한다.

        private int offset; // 어디까지 전달했는지 -> 현재 마지막으로 전달한 부분 -> 실제로 데이터를 준 마지막

        // Hot Publisher가 item을 더 이상 생성하지 않는 상황에 100개의 요청이 왔다면 줄 수 없고, 이 정보를 미리 저장해두자 -> 요청이 이만큼 필요해!
        private int requiredOffset;

        // 여러 subscriber에 대한 데이터를 저장해둘 객체
        private final Flow.Subscriber<? super Integer> subscriber;
        private final ExecutorService subscriptionExecutorService = Executors.newSingleThreadExecutor(); // 별도의 스레드에서 동작시키기 위함

        public SimpleHotSubscription(Flow.Subscriber<? super Integer> subscriber) {
            // 생성자로 인덱스의 마지막 값을 집어넣음 -> 객체가 생성될 때 마지막 인덱스의 값을 가지고 생성 -> 뭔가 작업이 생긴다면 그 이후부터 값을 가져갈것임.
            int lastElementIndex = numbers.size() -1;
            this.offset = lastElementIndex;
            this.requiredOffset = lastElementIndex;
            this.subscriber = subscriber;
        }
        @Override
        public void request(long n) {
            // 요청이 n개 만큼 들어오면 requiredOffset에 추가
            requiredOffset += n;

            // offset은 numbers의 사이즈보다 커지면 안된다. -> 생성된 값보다 커지면 당연히 안된다..
            onNextWhilePossible();
        }

        @Override
        public void cancel() {
            // 더이상 받지 않겠다.
            this.subscriber.onComplete();
            if (subscriptions.contains(this)) {
                subscriptions.remove(this);
            }
            subscriptionExecutorService.shutdown(); // 별도로 동작하던 스레드를 shutdown
        }

        public void wakeUp() {
            // 새로운 값이 생겼으니, subscriber에게 값을 전달 가능한 상황이면 전달해라.

            // request 메서드와 동일하게 동작한다. -> 클라이언트가 요청했는지, subscriber가 요청을 한것인지 차이..
            onNextWhilePossible();
        }

        private void onNextWhilePossible() {
            // 계속 가능한 시간동안 onNext를 하겠다는 함수
            subscriptionExecutorService.submit(() -> {
                while(offset < requiredOffset && offset < numbers.size()) {
                    var item = numbers.get(offset);
                    subscriber.onNext(item);
                    offset++;
                }
            });
        }

    }

}