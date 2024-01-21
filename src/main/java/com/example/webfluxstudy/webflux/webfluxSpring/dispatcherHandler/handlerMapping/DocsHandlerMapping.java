package com.example.webfluxstudy.webflux.webfluxSpring.dispatcherHandler.handlerMapping;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.dispatcherHandler.handlerMapping
 * fileName : DocsHandlerMapping
 * author : taeil
 * date : 1/21/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/21/24        taeil                   최초생성
 */
public class DocsHandlerMapping {
    // getHandler 메서드가 인터페이스로 제공된다.
    // getHandler : ServerWebExchagne가 주어지면 hnalder를 Mono로 반환한다.
    // 지원하는 handler가 없다면, Mono.empty로 반환한다.

    // 대표적인 HandlerMapping 과 각 mapping이 반환하는 handler 타입
    // 1. RequestMappingHanlerMapping -> HandlerMethod
    // 2. RouterFunctionMapping -> HandlerFunction
    // 3. SimpleUrlHandlerMapping -> WebHandler, WebSocketHandler
}