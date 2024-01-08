package com.example.webfluxstudy.webflux.webHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler
 * fileName : WebHandlerOnlyQueryParamExample
 * author : taeil
 * date : 1/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/8/24        taeil                   최초생성
 */
@Slf4j
public class WebHandlerOnlyQueryParamExample {
    // ServerWebExchange 로부터 request , response 를 획득
    // request의 getQueryParam
    public static void main(String[] args) throws InterruptedException {
        var webHandler = new WebHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                final ServerHttpRequest request = exchange.getRequest();
                final var response = exchange.getResponse();

                String nameQeury = request.getQueryParams().getFirst("name");
                String name = nameQeury == null ? "world" : nameQeury;

                String content = "Hello " + name;
                log.info("responseBody : {}", content);
                var responseBody = Mono.just(
                        response.bufferFactory()
                                .wrap(content.getBytes())
                );
                response.addCookie(
                        ResponseCookie.from("name", name).build());
                response.getHeaders()
                        .add("Content-Type", "text/plain");
                return response.writeWith(responseBody);
            }
        };
        final HttpHandler webHttpHandler = WebHttpHandlerBuilder
                .webHandler(webHandler)
                .build();

        var adapter = new ReactorHttpHandlerAdapter(webHttpHandler);
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();
        // 여기까지 보면 HttpHandler와 별 다른게 안보이는데, 다른점은 WebHttpHandlerBuilder에 주목할 필요가 있음~
    }
}