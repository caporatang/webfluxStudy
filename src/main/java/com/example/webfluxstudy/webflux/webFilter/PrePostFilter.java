package com.example.webfluxstudy.webflux.webFilter;

import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webFilter
 * fileName : PrePostFilter
 * author : taeil
 * date : 1/15/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/15/24        taeil                   최초생성
 */
@Slf4j
public class PrePostFilter {
    // chain.filter를 호출하기 전에 로직을 수행하면 servlet 스택 HandlerInterceptor의 preHandle과 동일하다.
    // chain.filter를 호출한 후 chaining을 하여 로직을 수행하면 HandlerInterceptor의 postHandle과 동일하다.

    public static void main(String[] args) {
        var preFilter = new WebFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                log.info("pre filter");
                // 체인의 필터를 실행하기 전이라면 preFilter
                // 다음으로 넘기겠다..
                return chain.filter(exchange);
            }
        };

        var afterFilter = new WebFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                return chain.filter(exchange)
                        .doOnSuccess(v -> {
                            // 어느 시점의 모노를 반환하는가?
                            // complete 되는 시점이다.
                            log.info("after filter");
                        });
            }
        };

        //final HttpHandler webHttpHandler = WebHttpHandlerBuilder
        //        .webHandler(webHandler)
        //        .filter(preFilter, afterFilter)
        //        .build();
    }
}