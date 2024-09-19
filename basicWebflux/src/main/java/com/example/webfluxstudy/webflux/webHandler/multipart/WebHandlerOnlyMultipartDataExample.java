package com.example.webfluxstudy.webflux.webHandler.multipart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Mono;

import java.awt.image.DataBuffer;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.multipart
 * fileName : WebHandlerOnlyMultipartDataExample
 * author : taeil
 * date : 1/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/13/24        taeil                   최초생성
 */
@Slf4j
public class WebHandlerOnlyMultipartDataExample {
    // name 필드의 값에 접근하기 위해 FormFieldPart로 cast하여 value()로 접근

    public static void main(String[] args) {
        var webHandler = new WebHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                final var response = exchange.getResponse();

                return exchange.getMultipartData().map(multipartData -> {
                    return ((FormFieldPart)multipartData.getFirst("name")).value();
                }).flatMap(nameQuery -> {
                    String name = nameQuery == null ? "world" : nameQuery;

                    String content = "Hello " + name;
                    log.info("resposneBody : {}", content);
                    var responseBody = Mono.just(
                            response.bufferFactory()
                                    .wrap(content.getBytes())
                    );

                    response.addCookie(
                            ResponseCookie.from("name", name).build()
                    );
                    response.getHeaders()
                            .add("Content-Type", "text/plain");
                    return response.writeWith(responseBody);
                });
            }
        };
    }
}