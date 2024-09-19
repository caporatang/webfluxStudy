package com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments
 * fileName : DocsReturnValue
 * author : taeil
 * date : 1/25/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/25/24        taeil                   최초생성
 */
@Controller
public class ReturnValueExample {
    // Servlet stack 의 return value와 거의 비슷하다.
    // 하지만 ModelAndView 대신 Rendering을 지원한다.
    // void를 반환할때 필요로하는 argument 차이다.

    // Flux<ServerSendEvent>, Observable<ServerSendEvent> 를 지원한다.
    // Servlet stack에서는 HttpMessageConverter를 사용하지만 reactive stack 에서는 HttpMessageWriter를 사용한다.

    // 1. return void
    // Mono<Void> 를 반환하거나 Null을 반환하는 경우
    // ServereHttpResponse, ServerWebExchange, @ResponseStatus가 제공된다면, response 객체로 컨트롤한다.
    // 만약 위의 조건에 해당하지 않는다면 restController에서 null을 반환한 경우 비어있는 body를 , html controller에서는 defaul view name을 가르킨다.

    // return void
    @GetMapping("/void-shr")
    Mono<Void> monoVoid(ServerHttpResponse serverHttpResponse) {
        // MOno<Void> 를 반환하고
        // ServerHttpResponse가 method argument로 주어졌다면 ServerHttpResponse를 통해서 응답을 반환한다.
        // ServerWebExchange도 동일하다.
        return serverHttpResponse.writeWith(
                Mono.just(serverHttpResponse.bufferFactory().wrap("hello wrold".getBytes()))
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/void-status")
    Void voidStatus() {
        // Void를 반환하고 @ResponseStatus가 주어졌다면 텅 빈 body와 ResponseStatus로 주어진 상태가 전달된다.
        return null;
    }

    @ResponseBody
    @GetMapping("/void")
    Void voidEmptyResp() {
        // Void를 반환하고, @ResponseBody를 가지고 있다면 텅 빈 body와 200 status 전달
        return null;
    }

}