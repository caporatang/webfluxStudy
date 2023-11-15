package com.example.webfluxstudy.basicReactiveProgramming.functionalInterface.blockingNonBlocking;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * packageName : com.example.webfluxstudy.blockingNonBlocking
 * fileName : D
 * author : taeil
 * date : 2023/09/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/30        taeil                   최초생성
 */
@Slf4j
public class D {
    public static void main(String[] args) {
        // main은 getResult의 결과에 관심이 없다. -> 비동기
        // getResult를 호출한 후, getResult가 완료되지 않더라도 main은 본인의 일을 할 수 있다. -> non-blocking
        // -> D 는 비동기 non-block으로 동작되는 모델이다.
        log.info("Start main");
        getResult(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                var nextValue = integer + 1;
                assert nextValue == 1;
            }
        });
        log.info("Finish main");
    }

    private static void getResult(Consumer<Integer> callbackc) {
        // 별도의 스레드로 동작된다.
        var excutor = Executors.newSingleThreadExecutor();
        try {
            excutor.submit(new Runnable() {
                @Override
                public void run() {
                    log.info("Start getResult");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    var result = 0;
                    try {
                        callbackc.accept(result);
                    } finally {
                        log.info("Finish getResult");
                    }

                }
            });
        } finally {
            excutor.shutdown();
        }
    }
}