package com.example.mongochat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.chatapplicationexample.config
 * fileName : WebSocketConfig
 * author : taeil
 * date : 2/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/17/24        taeil                   최초생성
 */
@Configuration
public class WebSocketConfig {
    // WebSocketService 는 webFilter와 비슷한 역할을함

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter(webSocketService());
    }

    @Bean
    WebSocketService webSocketService() {
        HandshakeWebSocketService webSocketService =  new HandshakeWebSocketService() {
            @Override
            public Mono<Void> handleRequest(ServerWebExchange exchange, WebSocketHandler handler) {
                // x-i-am 에서 값을꺼내자
                String iam = exchange.getRequest().getHeaders().getFirst("X-I-AM");
                if(iam == null) {
                    // iam이 null 이라면 컴플리트 시켜버림
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
                // attribute를 설정해서 넘겨주자
                return exchange.getSession()
                        .flatMap(session -> {
                            // session에 iam에 값을 세팅해서 반환
                            session.getAttributes().put("iam", iam);
                            return super.handleRequest(exchange, handler);
                        });
            }
        };
        // attribute 설정을 전부 다 켜준다 키지 않으면, webSocketHandler에게 넘기는데 옵션이 설정되어 있지 않으면 attribute가 전달되지 않는다.
        webSocketService.setSessionAttributePredicate(s -> true);
        return webSocketService;
    }
}