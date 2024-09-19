package com.example.webfluxstudy.webflux.codec;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Mono;

import java.awt.image.DataBuffer;

/**
 * packageName : com.example.webfluxstudy.webflux.codec
 * fileName : WebHandlerOnlyFormDataExample
 * author : taeil
 * date : 1/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/10/24        taeil                   최초생성
 */
@Slf4j
public class WebHandlerOnlyFormDataExample {
    public static void main(String[] args) {
        var webHandler = new WebHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                final var request =  exchange.getRequest();
                final var response =  exchange.getResponse();

                // formData에 접근..~
                // exchange가 생성되는 순간에 initForm Data 를 통해서 request에서 값을 읽어들일수있는 reader를 찾았고,
                // readMono를 통해서 나중에 값이 필요한 시점에 값을 읽어들이고 그 결과를 cache해서 결과를 내려준다.
                return exchange.getFormData().flatMap(formData -> {
                    // formData는 multiValueMap이다.
                    String nameQuery = formData.getFirst("name");
                    String name = nameQuery == null ? "world" : nameQuery;

                    String content = "Hello " + name;
                    log.info("responseBody : {}", content);

                    var responseBody = Mono.just(
                            response.bufferFactory().wrap(content.getBytes())
                    );

                    response.addCookie(ResponseCookie.from("name", name).build());
                    response.getHeaders()
                            .add("Content-Type", "text/plain");
                    return response.writeWith(responseBody);
                });
            }
        };
    }
}