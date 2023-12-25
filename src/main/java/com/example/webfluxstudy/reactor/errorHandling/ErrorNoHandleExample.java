package com.example.webfluxstudy.reactor.errorHandling;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling
 * fileName : ErrorNoHandleExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class ErrorNoHandleExample {
    // 에러 핸들링이 없는 경우
    // source나 연산자에서 에러가 발생했지만 따로 처리하지 않은 경우 기본적으로 onErrorDropped가 호출된다.
    // onErrorDropped의 기본 구현은 에러를 출력한다.
    public static void main(String[] args) {
        Flux.create(sink -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sink.error(new RuntimeException("error"));
        }).subscribe();

    }
}