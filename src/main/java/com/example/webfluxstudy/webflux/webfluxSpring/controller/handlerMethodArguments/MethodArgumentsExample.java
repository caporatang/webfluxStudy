package com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments
 * fileName : DocsMethodArguments
 * author : taeil
 * date : 1/25/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/25/24        taeil                   최초생성
 */
@Controller
public class MethodArgumentsExample {
    // Servlet stack 의 Controller method argument와 거의 비슷
    // 하지만 servlet stack 에서 제공되지 않고 reactive stack에서만 제공되거나 같은 역할을 하지만 다른 객체들이 제공된다.

    @GetMapping("/swe")
    Mono<String> serverWebExchange(ServerWebExchange serverWebExchange) {
        // 1. ServerWebExchange : method argument로 ServerWebExchange 접근 가능
        String name = serverWebExchange.getRequest()
                .getQueryParams()
                .getFirst("name");
        if(name == null) name = "world";

        return Mono.just("Hello " + name);
    }

    @GetMapping("/reqres")
    Mono<String> serverReqRes(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 2. ServerHttpReqeust / Response : method argument로 ServerHttpRequest, ServerHttpResponse접근 가능
        String name = serverHttpRequest
                .getQueryParams()
                .getFirst("name");
        if(name == null) name = "world";

        serverHttpResponse.setStatusCode(HttpStatus.CREATED);
        return Mono.just("Hello " + name);
    }

    @GetMapping("/session")
    Mono<String> session(WebSession webSession, ServerWebExchange serverWebExchange) {
        // 3. WebSession : servlet stack의 HttpSession과 비슷하다.
        // 3-1 새로운 attribute가 추가되기 전까지 새로운 session을 강제하지 않는다.
        // 3-2 HttpSession은 session을 강제로 생성해서 절대 Null이 될 수 없다.
        // 3-3 reactive type을 지원한다.
        // 3-4 재요청시 Session이 Cookie로 전달되고 다른 name을 전달해도 session의 값을 사용해서 응답을 반환한다.
        String savedName = webSession.getAttribute("name");
        String name;
        if(savedName != null) {
            name = savedName;
        } else {
            name = serverWebExchange.getRequest()
                    .getQueryParams()
                    .getFirst("name");

            if(name == null) name = "world";
            webSession.getAttributes().put("name", name);
        }
        return Mono.just("Hello " + name);
    }
}