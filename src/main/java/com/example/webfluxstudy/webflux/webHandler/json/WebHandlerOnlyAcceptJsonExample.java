package com.example.webfluxstudy.webflux.webHandler.json;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.NamedBeanHolder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.json
 * fileName : WebHandlerOnlyAcceptJsonExample
 * author : taeil
 * date : 1/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/13/24        taeil                   최초생성
 */
@Slf4j
public class WebHandlerOnlyAcceptJsonExample {
    // codecConfigurer를 생성하고
    // exchange와 codecConfigurer로 ServerRequest를 만들어서
    // serverRequest.bodyToMono로 json을 객체로 변경한다.

    // 결과 자체가 Json을 직접 처리해야하는 경우...에 사용하는건데 webHasndler에서 바로 처리하기엔 복잡한거같은데

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var codecConfigurer = ServerCodecConfigurer.create();

        var webHandler = new WebHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                final ServerRequest request = ServerRequest.create(
                        exchange,
                        codecConfigurer.getReaders()
                );
                final ServerHttpResponse response = exchange.getResponse();

                final var bodyMono = request.bodyToMono(NameHolder.class);
                return bodyMono.flatMap(nameHolder -> {
                    String nameQuery = nameHolder.name;
                    String name = nameQuery == null ? "world" : nameQuery;

                    String content = "Hello " + name;
                    log.info("responseBody: {}", content);
                    Mono<DataBuffer> responseBody = Mono.just(
                            response.bufferFactory()
                                    .wrap(content.getBytes())
                    );

                    response.getHeaders()
                            .add("Content-Type", "text/plain");
                    return response.writeWith(responseBody);
                });
            }
        };

        final HttpHandler webHttpHandler = WebHttpHandlerBuilder
                .webHandler(webHandler)
                .build();

        final var adapter = new ReactorHttpHandlerAdapter(webHttpHandler);
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();
    }

    @Data
    private static class NameHolder {
        private String name;
    }
}