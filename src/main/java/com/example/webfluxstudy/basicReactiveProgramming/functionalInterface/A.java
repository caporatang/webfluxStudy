package com.example.webfluxstudy.basicReactiveProgramming.functionalInterface;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.functionalInterface
 * fileName : A
 * author : taeil
 * date : 2023/09/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/25        taeil                   최초생성
 */
@Slf4j
public class A {
    // 해당 클래스는   main : caller   getResult : callee

    // A 클래스 --> 함수 호출 관점에서의 동기 -> caller와 callee가 동기화되어 있다.
    // main(caller)은 getResult의 결과에 관심이 있다.
    // main은 결과를 이용해서 다음 코드를 실행한다.

    // caller는 callee의 결과에 관심이 있다.
    // caller는 결과를 이용해서 action을 수행한다.

    public static void main(String[] args) {
        log.info("Start main");

        // caller가 callee(getResult) 호출
        var result = getResult();

        // callee가 반환해준 value를 가지고 다음 excute(액션)을 수행 -> +1 , 비교
        var nextValue = result + 1;
        assert nextValue == 1;

        log.info("Finish main");
    }

    private static int getResult() {
        log.info("Start getResult");

        try {
            Thread.sleep(1000); // 1초 슬립! 메인 함수는 최소한 1초의 시간이 걸린다.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var result = 0;

        try {
            return result;
        } finally {
            log.info("Finish getResult");
        }


    }

}