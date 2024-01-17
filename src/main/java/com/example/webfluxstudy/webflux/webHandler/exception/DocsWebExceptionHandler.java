package com.example.webfluxstudy.webflux.webHandler.exception;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.exception
 * fileName : DocsWebExceptionHandler
 * author : taeil
 * date : 1/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/17/24        taeil                   최초생성
 */
public class DocsWebExceptionHandler {
    // ServerWebExchange와 Throwable을 인자로 받는다.
    // WebHandler에서 에러가 발생한 경우, WebExceptionHandler에 exchange와 throwable을 전달한다.
    // 처리가 완료되면 Mono<Void> 를 반환한다.
    // 상태를 변경하고 body를 write하거나, Mono.error를 반환하여 error를 처리하지 않고 다음 exceptionHandler에게 pass한다.
}