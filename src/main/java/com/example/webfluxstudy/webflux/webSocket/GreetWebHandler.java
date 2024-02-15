package com.example.webfluxstudy.webflux.webSocket;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.webflux.webSocket
 * fileName : GreetWebHandler
 * author : taeil
 * date : 2/15/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/15/24        taeil                   최초생성
 */
public class GreetWebHandler implements WebHandler {
    // /sumg-greet으로 요청을 보내면 매칭되는 WebHandler, 여기서는 GreetWebHandler 가 응답을 처리한다.
    // SimpleUrlHandlerMapping에 webScoekt을 연결한다면 ?

    @Override
    public Mono<Void> handle(ServerWebExchange exchange) {
        String name = exchange.getRequest().getQueryParams()
                .getFirst("name");

        if (name == null) name = "world";

        return exchange.getResponse().writeWith(
                Mono.just(exchange.getResponse()
                        .bufferFactory()
                        .wrap(("Hello " + name)
                                .getBytes(StandardCharsets.UTF_8))
                )
        );
    }
}