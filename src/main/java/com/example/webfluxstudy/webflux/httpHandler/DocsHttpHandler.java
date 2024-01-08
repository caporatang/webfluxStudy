package com.example.webfluxstudy.webflux.httpHandler;

/**
 * packageName : com.example.webfluxstudy.webflux.httpHandler
 * fileName : DocsHttpHandler
 * author : taeil
 * date : 1/3/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/3/24        taeil                   최초생성
 */
public class DocsHttpHandler {
    // Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response);
    // ServerHttpRequest 와 ServerHttpResponse를 인자로 받고,
    // 응답을 돌려줘야 하는 시점을 반환하는 함수형 인터페이스임

    // ServerHttpRequest 는 ReactiveServerHttpInputMessage 와 HttpRequest 상속
    // ServerHttpResponse 는 ReactiveHttpOutputMessage 를 상속

    // ServerHttpInputMessage , HttpRequest , ReactiveHttpOutputMessage 는 HttpMessage를 상속하고 있음 !

    

}