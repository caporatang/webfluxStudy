package com.example.webfluxstudy.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.lang.Nullable;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

import java.util.List;
import java.util.function.Consumer;

/**
 * packageName : com.example.webfluxstudy.reactor
 * fileName : DocsSubscribe
 * author : taeil
 * date : 11/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/27/23        taeil                   최초생성
 */
@Slf4j
public class DocsSubscribe {
    // Subscribe
    public static void main(String[] args) {
        // publisher에 subscribe 하지 않으면 아무 일도 생기지 않는다.
        Flux.fromIterable(List.of(1,2,3,4,5))
                .doOnNext(value -> {
                    log.info("value : {}", value);
                });

        // subscribe의 종류는 다음과 같다.
        // 1. consumer를 넘기지 않는 subscribe : 별도로 consume을 하지 않고 최대한으로 요청한다.
        // public final Disposable subscribe()

        Flux.fromIterable(List.of(1,2,3,4,5))
            // Publisher 에서 아이템을 만드는 것이 중요한 경우
            // 별도의 consume을 하지 않는다.
            // 결과를 확인하기 위해서 doOnNext를 이용한다.
            // doOnNext : 파이프라인에 영향을 주지 않고 지나가는 값을 확인하는 메서드다.
            .doOnNext(value -> {
                        log.info("value : {}", value);
                    }
            ).subscribe();

        // 2. 함수형 인터페이스 기반의 subscribe : Disposable을 반환하고 disposable을 통해서 언제든지 연결 종료 가능하다.
        // public final Disposable subscribe(
        //@Nullable Consumer<? super T> consumer,
        //@Nullable Consumer<? super Throwable> errorConsumer,
        //@Nullable Runnable completeConsumer,
        //@Nullable Context initialContext )

        // 함수형 인터페이스를 subscribe에 제공 -> 각각의 consumer는 null이 가능하다.
        Flux.fromIterable(List.of(1,2,3,4,5))
                .subscribe(new Consumer<Integer>() {
                               @Override
                               public void accept(Integer integer) {
                                   // consumer : 값을 인자로 받아야 하기 때문에 Consumer 함수형 인터페이스를 구현한다.
                                   log.info("value : {}", integer);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) {
                                   // errorConsumer : 에러를 인자로 받아야 하기 때문에 Consumer를 구현한다.
                                   log.error("error, {}", throwable);
                               }
                           }, new Runnable() {
                               @Override
                               public void run() {
                                   // completeConsumer : 받을 인자가 없기 때문에 Runnable 구현 -> complete 이벤트가 발생했다는게 중요한것이기 떄문에 인자를 받지 않는다.
                                   log.info("complete");
                               }
                               // initialContext : upstream에 전달할 context이다.
                           }, Context.empty()
                );
        // 함수형 인터페이스기 떄문에, 람다식으로 변경하여 처리가 가능하다.
        // subscription을 받을 수 없기 때문에 back-pressure 이용이 불가능하다.
        // 마지막 인자로 Consumer<? super Subscription> subscriptionConsumer를 받는 형태도 있지만 deprecated 이다.
        // deprecated 이유 : 개발자들이 request 호출을 잊는 경향이 있다. -> 따라서 backPressure가 필요한 경우 함수형 인터페이스 기반을 사용하지말고, Subscriber 기반의 subscribe가 권장된다.


        // 3. Subscriber 기반의 subscribe : Subscriber는 subscription을 받기 때문에 request와 cancel을 통해서 backpressure 조절과 연결 종료가 가능하다.
        // public final void subscribe(Subscriber<? super T> actual)
        Flux.fromIterable(List.of(1,2,3,4,5))
                .subscribe(new Subscriber<Integer>() {
                    // Subscriber 구현체를 subscribe에 전달한다.
                    @Override
                    public void onSubscribe(Subscription s) {
                        // onSubscribe를 통해서 subscription을 받고 즉시 Long.Max_VALUE 개 만큼 request
                        // unbounded request : Publisher에서 제공할 수 있는 데이터를 최대한 요청한다. -> 제한이 없는 request
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        log.info("value : {}", integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        log.error("error: {}", t);
                    }

                    @Override
                    public void onComplete() {
                        log.info("complete");
                    }
                });
        
    }
}