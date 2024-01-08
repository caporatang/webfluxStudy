package com.example.webfluxstudy.webflux.httpHandler.serverHttpResponse;

/**
 * packageName : com.example.webfluxstudy.webflux.httpHandler.serverHttpResponse
 * fileName : DocsHttpHandler
 * author : taeil
 * date : 1/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/8/24        taeil                   최초생성
 */
public class DocsHttpHandler {
    // ServerHttpRequest와 ServerHttpResponse를 전달
    // http 요청 처리가 끝나면 Mono<Void> 반환
    // - ServerHttpResponse의 setComplete 혹은 writeWith가 Mono<Void> 를 반환하기 때문에 이 결과를 반환하는 경우가 많다.
}