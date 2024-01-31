package com.example.webfluxserverexample.filter;

import com.example.webfluxserverexample.auth.IamAuthentication;
import com.example.webfluxserverexample.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;


/**
 * packageName : com.example.webfluxserverexample
 * fileName : SecurityWebFilter
 * author : taeil
 * date : 1/31/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/31/24        taeil                   최초생성
 */
@Component
@RequiredArgsConstructor
public class SecurityWebFilter implements WebFilter {
    private final AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final ServerHttpResponse resp = exchange.getResponse();

        String iam = exchange.getRequest().getHeaders()
                .getFirst("X-I-AM"); // 헤더는 MultiValue 일수있음

        if(iam == null) {
            resp.setStatusCode(HttpStatus.UNAUTHORIZED);
            return resp.setComplete();
        }

        return authService.getNameByToken(iam)
                .map(IamAuthentication::new)
                .flatMap(authentication -> {
                    return chain.filter(exchange)
                            .contextWrite(context -> {
                                Context newContext = ReactiveSecurityContextHolder
                                        .withAuthentication(authentication);

                                return context.putAll(newContext);
                            });
                })
                .switchIfEmpty(Mono.defer(() -> {
                    resp.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return resp.setComplete();
                }));
    }
}