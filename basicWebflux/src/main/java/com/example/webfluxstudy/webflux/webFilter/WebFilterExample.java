package com.example.webfluxstudy.webflux.webFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.awt.image.DataBuffer;

/**
 * packageName : com.example.webfluxstudy.webflux.webFilter
 * fileName : WebFilterExample
 * author : taeil
 * date : 1/15/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/15/24        taeil                   최초생성
 */
@Slf4j
public class WebFilterExample {
    // request의 header로부터 X-Custom-Name에 접근
    // name이 null 이라면 400으로 만들고 complete -> name이 Null이 아니라면 attribute에 name을 추가.
    // request.mutate()로 builder를 만들고 header에서 X-CUstom-Name을 제거
    // exchange.mutate()로 builder를 만들고 변경된 newReq를 전달한다.

    public static void main(String[] args) throws InterruptedException {
        var extractNameFromHeaderFilter = new WebFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                log.info("extractNameFromHeaderFilter");
                final ServerHttpRequest request = exchange.getRequest();
                final ServerHttpResponse response = exchange.getResponse();

                String name = request.getHeaders().getFirst("X-Custom-Name");

                if(name == null) {
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                    return response.setComplete();
                } else {
                    exchange.getAttributes().put("name", name);
                    var newReq = request.mutate() // builder에 접근, remove 후 다시 build해서 새로운 request를 만들어서 Filter에 넘긴다.
                            .headers(h -> h.remove("X-Custom-Name"))
                            .build();
                    return chain.filter(
                            exchange.mutate().request(newReq).build()
                    );
                }
            }
        };

        // postFilter
        var timeLoggingFilter = new WebFilter() {
            // chain.filter를 호출하기 전에 startTime을 측정
            // chain.filter가 success된 후에 endTIme을 측정하여 시간 차이를 기록한다.
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                log.info("timeLoggingFilter");
                // 시작하기전에 나노타임으로 시작시간을 저장한다.
                long startTime = System.nanoTime();
                return chain.filter(exchange)
                        .doOnSuccess(v -> {
                            // webHandler에서 응답을 처리하고 나면 시간을 계산해서 출력
                            long endTime = System.nanoTime();
                            log.info("time : {} ms", (endTime - startTime)/1000000.0);
                        });
            }
        };

        var webHandler = new WebHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                log.info("web handler");
                final  ServerHttpRequest request = exchange.getRequest();
                final ServerHttpResponse response = exchange.getResponse();

                final String nameHeader = request.getHeaders().getFirst("X-Custom-Name");
                log.info("X-Custom-Name : {}", nameHeader);

                String name = exchange.getAttribute("name");
                String content = "Hello " + name;

                var responseBody = Mono.just(
                        response.bufferFactory().wrap(content.getBytes())
                );

                response.getHeaders()
                        .add("Contentg-Type", "text/plain");
                return response.writeWith(responseBody);
            }
        };

        final HttpHandler webHttpHandler = WebHttpHandlerBuilder
                // filter는 실행 순서대로 넣어주자
                .webHandler(webHandler)
                .filter(
                        extractNameFromHeaderFilter,
                        timeLoggingFilter
                )
                .build();

        var adapter = new ReactorHttpHandlerAdapter(webHttpHandler);
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();
    }
}