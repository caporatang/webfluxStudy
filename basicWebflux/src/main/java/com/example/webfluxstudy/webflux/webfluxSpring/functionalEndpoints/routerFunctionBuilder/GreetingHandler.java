package com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.routerFunctionBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.routerFunctionBuilder
 * fileName : GreetingHandler
 * author : taeil
 * date : 1/23/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/23/24        taeil                   최초생성
 */
@Slf4j
public class GreetingHandler {
    public static Mono<ServerResponse> greetQueryParam(ServerRequest serverRequest) {
        // serverRequest의 queryParam을 이용해서 name 필드에 접근
        String name = serverRequest.queryParam("name")
                .orElse("world");

        String content = "Hello " + name;
        return ServerResponse.ok().bodyValue(content);
    }

    public static Mono<ServerResponse> greetPathVariable(ServerRequest serverRequest) {
        // pathVariable 로부터 name에 해당하는 값을 받는다.
        String name = serverRequest.pathVariable("name");

        String content = "Hello " + name;
        return ServerResponse.ok().bodyValue(content);
    }

    public static Mono<ServerResponse> greetJsonBody(ServerRequest serverRequest) {
        // serverRequest.bodyToMono로 body를 NameHolder 객체로 만들고 getName 으로 이름에 접근
        return serverRequest.bodyToMono(NameHolder.class)
                .map(NameHolder::getName)
                .map(name -> "Hello " + name)
                // mono를 까서 일반 mono를 반환
                .flatMap(content -> ServerResponse.ok().bodyValue(content))
                .doOnError(throwable -> log.error("error", throwable));
    }

    public static Mono<ServerResponse> greetPlainTextBody(ServerRequest serverRequest) {
        // server.bodyToMono로 body를 String으로 읽어서 처리
        return serverRequest.bodyToMono(String.class)
                .map(name -> "Hello " + name)
                .flatMap(content -> ServerResponse.ok().bodyValue(content));
    }

    public static Mono<ServerResponse> greetHeader(ServerRequest serverRequest) {
        // serverRequest.headers를 이용하여 X-Custom-Name 헤더를 읽어서 처리
        String name = serverRequest.headers().header("X-Custom-Name")
                .stream()
                .findFirst()
                .orElse("world");

        String content = "Hello " + name;
        return ServerResponse.ok().bodyValue(content);
    }
}