package com.example.webfluxstudy.webflux.structure.reactorNetty;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRoutes;

import java.util.function.Consumer;

/**
 * packageName : com.example.webfluxstudy.webflux.structure.reactorNetty
 * fileName : ReactorNettyHttpExample
 * author : taeil
 * date : 1/3/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/3/24        taeil                   최초생성
 */
@Slf4j
public class ReactorNettyHttpExample {
    // Reactor Netty
    // Reactor 기반으로 Netty를 wrapping
    // Netty의 성능과 Reactor의 조합성, 편의성 모두 제공

    public static void main(String[] args) {
        Consumer<HttpServerRoutes> routesConsumer = routes ->
                // hello 라는 routes로 요청이 들어오면 request, response를 모두 받고
            routes.get("/hello", (request, response) -> {
                        var data = Mono.just("Hello, World");
                        // routesConsumer를 만듬
                        return response.sendString(data);
                    }
            );
        // 만들어진 routesConsumer를 실행
        HttpServer.create()
                .route(routesConsumer)
                .port(8080)
                .bindNow()
                .onDispose()
                .block();
        }
    }
