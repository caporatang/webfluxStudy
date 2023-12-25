package com.example.webfluxstudy.reactor.errorHandling.onErrorReturn;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling.onErrorReturn
 * fileName : OnErrorReturnAfterExecuteExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class OnErrorReturnAfterExecuteExample {
    // OnErrorReturn 에 고정값이 아니라 함수를 넘긴다면 ?
    // 고정된 값을 넘기기 위해, 함수를 실행하면 문제가 발생할 수 있다.
    // 에러가 발생하지 않아도 무조건 함수가 실행되고 실행된 값을 사용하기 때문이다.
    
    public static void main(String[] args) {
        log.info("start main");

        Flux.just(1)
                .onErrorReturn(shouldDoOnError())
                .subscribe(value -> {
                    log.info("value : " + value);
                });
        log.info("end main");
    }

    private static int shouldDoOnError() {
        log.info("shouldDoOnError");
        return 0;
    }
}