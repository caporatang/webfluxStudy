package com.example.webfluxstudy.reactor.errorHandling.onErrorReturn;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling
 * fileName : OnErrorReturnExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class OnErrorReturnExample {
    // onError 이벤트를 처리하기 위해 고정된 값을 반환한다.

    public static void main(String[] args) {
        Flux.error(new RuntimeException("error")) // 에러가 발생하게 되면 아래로 쭉 전파된다.
                .onErrorReturn(0) // 고정된 값이 다음 subscribe에게 전달된다.
                .subscribe(value -> {
                    log.info("value : " + value);
                });
    }
}