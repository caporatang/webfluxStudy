package com.example.webfluxstudy.webflux.webHandler.multipart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.multipart
 * fileName : WebHandlerOnlyMultipartDataFileExample
 * author : taeil
 * date : 1/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/13/24        taeil                   최초생성
 */
@Slf4j
public class WebHandlerOnlyMultipartDataFileExample {
    // data 필드는 Data.json의 내용을 줄마다 나눠서 Flux<DataBuffer> 로 제공한다.
    // String 으로 변경하여 join 후 다음 파이프라인으로 전달한다.
    // 다음 step에서 ObjectMapper로 json으로 변환하여 Name 필드를 추출한다.

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var objectMapper = new ObjectMapper();

        var webHandler = new WebHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                final ServerHttpResponse response = exchange.getResponse();

                return exchange.getMultipartData().flatMap(multipartData -> {
                    return (multipartData.getFirst("data")).content() // content 에 접근 Content는 Flux<DataBuffer> 로 반환
                            .map(dataBUffer -> dataBUffer.toString(StandardCharsets.UTF_8)) // content에서 가지고온 한줄한줄의 결과를 스트링으로 변환
                            .reduce((s1, s2) -> s1 + s2); // reduce를 통해서 하나의 결과로 합침
                }).flatMap(json -> {
                    String name;
                    try {
                        name = objectMapper.readTree(json).get("name").asText();
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage());
                        name = "world";
                    }

                    String content = "Hello " + name;
                    log.info("responseBody: {}", content);
                    Mono<DataBuffer> responseBody = Mono.just(
                            response.bufferFactory()
                                    .wrap(content.getBytes())
                    );

                    response.addCookie(
                            ResponseCookie.from("name", name).build());
                    response.getHeaders()
                            .add("Content-Type", "text/plain");
                    return response.writeWith(responseBody);
                });
            }
        };

        final HttpHandler webHttpHandler = WebHttpHandlerBuilder
                .webHandler(webHandler)
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