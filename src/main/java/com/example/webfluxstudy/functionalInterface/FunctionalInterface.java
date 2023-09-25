package com.example.webfluxstudy.functionalInterface;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * packageName : com.example.webfluxstudy.functionalInterface
 * fileName : Test
 * author : taeil
 * date : 2023/09/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/25        taeil                   최초생성
 */
@Slf4j
public class FunctionalInterface {
    // 함수 호출 관점에서 동기와 비동기
    // 함수가 다른 함수를 호출하는 상황
    // Caller : 호출하는 함수
    // Callee : 호출 당하는 함수
    // ex) caller ---------------- call ----------------> callee : caller가 callee를 호출했다.

    // 함수형 인터페이스
    // 함수형 프로그래밍을 지원하기 위해 java 8부터 도입
    // 1개의 추상 메서드를 갖고 있는 인터페이스로 함수를 1급 객체로 사용할 수 있다.
    // 함수를 변수에 할당하거나 인자로 전달하고 반환값으로 사용 가능하다.
    // Function, Consumer, Supplier, Runnable등.. 이 있다.
    // 함수형 인터페이스를 구현한 익명 클래스를 람다식으로 변경 가능하다
    // 함수형 인터페이스는 호출한 쓰레드에서 실행된다.

    public static void main(String[] args) {
        var consumer = getConsumer();
        consumer.accept(1);

        var consumerAsLambda = getConsumerAsLambda();
        consumerAsLambda.accept(1);

        handleConsumer(consumer);

    }

    public static Consumer<Integer> getConsumer() {
        Consumer<Integer> returnValue = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                log.info("value in interface : {}", integer);
            }
        };
        return returnValue;
    }

    public static Consumer<Integer> getConsumerAsLambda() {
        return integer -> log.info("value in lambda : {}", integer);
    }

    public static void handleConsumer(Consumer<Integer> consumer) {
        log.info("handleConsumer");
        consumer.accept(1);
    }

}