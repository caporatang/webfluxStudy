package com.example.webfluxstudy.webflux.webHandler.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.exception
 * fileName : WebExceptionHandlerExample
 * author : taeil
 * date : 1/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/17/24        taeil                   최초생성
 */
@Slf4j
public class WebExceptionHandlerExample {
    // WebExceptionHandler를 구현하여 에러가 발생했을때 처리하는 컴포넌트를 추가한다.
    // Throwable에 instanceOf를 이용하여 처리할지 말지 결정한다.

    public static void main(String[] args) {
        var exceptionHandler = new WebExceptionHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
                final ServerHttpResponse response = exchange.getResponse();

                if(ex instanceof CustomException) {
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                    var respBody = response.bufferFactory().wrap(ex.getMessage().getBytes());

                    return response.writeWith(Mono.just(respBody));
                } else {
                    return Mono.error(ex);
                }
            }
        };
    }
}