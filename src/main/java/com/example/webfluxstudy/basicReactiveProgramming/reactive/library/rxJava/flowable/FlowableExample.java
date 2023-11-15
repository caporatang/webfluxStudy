package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.rxJava.flowable;

import com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.SimpleSubscriber;
import io.reactivex.rxjava3.core.Flowable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.flowable
 * fileName : FlowableExample
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
@Slf4j
public class FlowableExample {
    public static void main(String[] args) {
        // projectReactor와 비슷한 형태를 가짐
        log.info("start main");
        getItems()
                .subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Flowable<Integer> getItems() {
        return Flowable.fromIterable(List.of(1,2,3,4,5));
    }

}