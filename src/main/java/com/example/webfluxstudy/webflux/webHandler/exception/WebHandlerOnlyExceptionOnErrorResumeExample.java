package com.example.webfluxstudy.webflux.webHandler.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.exception
 * fileName : WebHandlerOnlyExceptionOnErrorResumeExample
 * author : taeil
 * date : 1/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/17/24        taeil                   최초생성
 */
@Slf4j
public class WebHandlerOnlyExceptionOnErrorResumeExample {
    // publisher 에서 Exception이 발생한 경우, onErrorResume을 이용해서 status를 변경하고 새로운 body를 write한다.

    public static void main(String[] args) {
        // 이렇게 구현해도 되지만.. 매번 이렇게 구현하는건 번거롭다.. -> webExceptionHandler 에게 위임해서 처리하게 하자
        var webhandler = new WebHandler() {

            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                final ServerHttpResponse response = exchange.getResponse();

                return response.writeWith(
                        Mono.create(sink -> {
                            sink.error(new CustomException("custom exception"));
                        }
                )).onErrorResume(CustomException.class, e -> {
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                    return response.writeWith(
                            Mono.just(response.bufferFactory().wrap(e.getMessage().getBytes()))
                    );
                });
            }
        };

    }

}