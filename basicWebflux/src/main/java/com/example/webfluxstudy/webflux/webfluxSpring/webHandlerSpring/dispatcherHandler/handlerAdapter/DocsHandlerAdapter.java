package com.example.webfluxstudy.webflux.webfluxSpring.webHandlerSpring.dispatcherHandler.handlerAdapter;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.dispatcherHandler.handlerAdapter
 * fileName : DocsHandlerAdapter
 * author : taeil
 * date : 1/21/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/21/24        taeil                   최초생성
 */
public class DocsHandlerAdapter {
    // supprots , handle 메서드 인터페이스

    // support : handlerMapping을 통해서 받은 handler를 지원하는지에 대한 여부를 판단 -> true라면 주어진 handler로 handle 실행 가능.
    // handle : ServerWebExchange와 handler를 받아서 요청을 처리하고 HandlerResult를 Mono로 반환한다.

    // 대표적인 HandlerAdapter 와 각 처리 가능한 handler
    // 1. RequestMappingHandlerAdapter -> HandlerMethod
    // 2. HandlerFunctionAdapter -> HandlerFunction
    // 3. SimpleHandlerAdapter -> WebHandler
    // 4. WebSocketHandlerAdapter -> WebSocketHandler
}