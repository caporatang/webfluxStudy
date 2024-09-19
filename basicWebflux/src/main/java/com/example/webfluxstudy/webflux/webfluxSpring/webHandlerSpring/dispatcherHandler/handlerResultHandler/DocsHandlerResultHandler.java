package com.example.webfluxstudy.webflux.webfluxSpring.webHandlerSpring.dispatcherHandler.handlerResultHandler;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.dispatcherHandler.handlerResultHandler
 * fileName : DocsHandlerResultHandler
 * author : taeil
 * date : 1/21/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/21/24        taeil                   최초생성
 */
public class DocsHandlerResultHandler {
    // support , handleResult 메서드
    // support : handlerAdapter를 통해서 받은 handlerResult를 지원하는지에 대한 여부, true라면 주어진 handlerREsult로 handlerResult 가능
    // handleResult : ServerWebExchange와 result를 받아서 응답 작업을 수행하고 작업이 완료된 시점을 Mono<Void> 로 반환한다

    // 대표적인 HandlerResultHandler 와 각 처리 가능한 handlerResultHandler (HandlerResult안에 있는 어떤 값을 처리 가능한가.)
    // 1. ResponseEntityResultHandler -> ResponseEntity, HttpHeaders
    // 2. ResponseBodyResultHandler -> @ResponseBody
    // 3. ViewResolutionResultHandler -> @ModelAttribute, String, Rendering, Model, Map, View
    // 4. ServerResponseResultHandler -> ServerResponse
}