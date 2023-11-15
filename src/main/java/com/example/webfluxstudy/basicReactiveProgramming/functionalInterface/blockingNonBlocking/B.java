package com.example.webfluxstudy.basicReactiveProgramming.functionalInterface.blockingNonBlocking;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * packageName : com.example.webfluxstudy.functionalInterface
 * fileName : B
 * author : taeil
 * date : 2023/09/26
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/26        taeil                   최초생성
 */
@Slf4j
public class B {
    // A 클래스와 동일하게 main : caller, getResult : callee

    // caller가 callee를 호출한다.
    // A 클래스와 비슷해보이지만 다른점은 바로 데이터를 반환하는게 아니라 Consumer(함수형 인터페이스)를 먼저 실행하고, 그 결과를 caller 에게 반환한다.
    // 즉, caller가 callee에게 Action(integer +1, assert nextValue ==1)을 위임했다고 볼수있다.

    // B 클래스 --> 함수 호출 관점에서의 비동기 --> 결과가 어떻건 상관이 없기 떄문에 동기화되어 있지 않다 라고 할수있음.
    // main은 getResult의 결과에 관심이 없다.
    // getResult는 결과를 이용해서 함수형 인터페이스를 실행한다.

    // caller는 callee의 결과에 관심이 없다.
    // callee는 결과를 이용해서 callback을 수행한다.

    public static void main(String[] args) {
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

    public static void getResult(Consumer<Integer> cb) {
        log.info("Start getResult");
        try {
           Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var result = 0;
        cb.accept(result);
        log.info("Finish getResult");
    }


}