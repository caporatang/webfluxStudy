package com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints
 * fileName : DocsServerRequest
 * author : taeil
 * date : 1/23/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/23/24        taeil                   최초생성
 */
public class DocsServerRequest {
    // RouterFunction, HandlerFunction을 지원하기 위해서 추가된 request 객체
    // ServerHttpRequest, ServerWebExchange의 메서드들을 대부분 지원한다.
    // 내부적으로 ServerWebExchange를 포함한다.
    // -> messageReaders를 제공하고 codec의 reader를 이용해서 body를 String이나 객체 등으로 쉽게 접근 가능하다.
}