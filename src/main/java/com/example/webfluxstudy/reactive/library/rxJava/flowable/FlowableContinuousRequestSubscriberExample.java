package com.example.webfluxstudy.reactive.library.rxJava.flowable;

import com.example.webfluxstudy.reactive.library.projectReactor.Flux.backPressure.ContinuousRequestSubscriber;
import io.reactivex.rxjava3.core.Flowable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.flowable
 * fileName : FlowableContinuousRequestSubscriberExample
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
@Slf4j
public class FlowableContinuousRequestSubscriberExample {
    public static void main(String[] args) {
        // Flux와 유사한 모양.
        log.info("start main");

        getItems().
                subscribe(new ContinuousRequestSubscriber<>());

        log.info("end main");
    }

    private static io.reactivex.rxjava3.core.Flowable<Integer> getItems() {
        return Flowable.fromIterable(List.of(1,2,3,4,5));
    }

}