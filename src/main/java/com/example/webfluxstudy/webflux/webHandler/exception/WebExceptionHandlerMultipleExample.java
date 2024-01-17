package com.example.webfluxstudy.webflux.webHandler.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.util.function.Function;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.exception
 * fileName : WebExceptionHandlerMultipleExample
 * author : taeil
 * date : 1/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/17/24        taeil                   최초생성
 */
@Slf4j
public class WebExceptionHandlerMultipleExample {
    // 만약 같은 타입의 익셉션을 여러개로 나눠서 처리하고싶다면 어떻게 해야할까?

    public static void main(String[] args) throws InterruptedException {
        Function<String, WebExceptionHandler> exceptionHandlerFactory = (body) ->
                (WebExceptionHandler) (exchange, ex) -> {
                    final ServerHttpResponse response = exchange.getResponse();
                    if(ex instanceof CustomException) {
                        response.setStatusCode(HttpStatus.BAD_REQUEST);
                        var respBody = response.bufferFactory().wrap(body.getBytes());

                        return response.writeWith(Mono.just(respBody));
                    } else {
                        return Mono.error(ex);
                    }
                 };

        var webHandler = new WebHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                final ServerHttpResponse response = exchange.getResponse();

                return response.writeWith(
                        Mono.create(sink -> {
                                    sink.error(new CustomException("custom exception"));
                                }
                        ));
            }
        };

        final HttpHandler webHttpHandler = WebHttpHandlerBuilder
                .webHandler(webHandler)
                .exceptionHandlers(webExceptionHandlers -> {
                    webExceptionHandlers.add(exceptionHandlerFactory.apply("3"));
                    webExceptionHandlers.add(exceptionHandlerFactory.apply("2"));
                    webExceptionHandlers.add(exceptionHandlerFactory.apply("1"));
                })
                .build();

        final var adapter = new ReactorHttpHandlerAdapter(webHttpHandler);
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();
    }
}