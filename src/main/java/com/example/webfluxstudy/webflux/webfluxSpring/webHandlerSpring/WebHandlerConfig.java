package com.example.webfluxstudy.webflux.webfluxSpring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring
 * fileName : WebHandlerConfig
 * author : taeil
 * date : 1/21/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/21/24        taeil                   최초생성
 */
@Slf4j
public class WebHandlerConfig {
    // webHandler bean을 만들어서 직접 등록한다면 ?
    // WebHttpBuilder를 사용하지 않더라도 등록한 webHandler bean을 가져다가 처리할 수 있다.

    // -> 실행 결과는 이미 등록한 bean이라고 등록되지 않는다 !
    // 이유는 DispatcherHandler bean 에 이미 webHandler로 등록되어 있기 때문이다.
    // WebFluxConfigurationSupprot 는 autoconfiguration으로 자동으로 등록한다.
    // DispatcherHandler가 기본 WebHandler로 등록한다.

    @Bean
    public WebHandler webHandler() {
        return exchange -> {
            final ServerHttpRequest request = exchange.getRequest();
            final ServerHttpResponse response = exchange.getResponse();

            String nameQuery = request.getQueryParams().getFirst("name");
            String name = nameQuery == null ? "world" : nameQuery;

            String content = "Hello" + name;
            log.info("responseBody : {}", content);

            Mono<DataBuffer> responseBody = Mono.just(
                    response.bufferFactory().wrap(content.getBytes())
            );

            response.getHeaders()
                    .add("Content-Type", "text/plain");
            return response.writeWith(responseBody);
        };
    }

}