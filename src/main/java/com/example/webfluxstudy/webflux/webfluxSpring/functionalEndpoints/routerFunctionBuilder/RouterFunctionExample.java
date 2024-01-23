package com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.routerFunctionBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.routerFunctionBuilder
 * fileName : RouterFunctionExample
 * author : taeil
 * date : 1/23/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/23/24        taeil                   최초생성
 */
@Slf4j
public class RouterFunctionExample {
    // path로 전체 prefix에 "greet" 을 추가
    // nest로 전체 요청에 Accept 헤더가 text/plain을
    // 첫 번째 GET : queryParam의 name 필드가 빈 값이 아닌지 확인
    // 두 번째 GET : pathVariable을 이용하여 name 전달
    // 세 번째 GET : header에 X-Custom-Name을 포함하는지 확인
    // 네 번째 POST : contentType 을 확인

    // path와 nest는 아래 일종의 파이프라인들의 중첩을 만드는 역할을하는것임.
    
    public static void main(String[] args) {
        RouterFunction<ServerResponse> router = route()
                .path("/greet", b1 -> b1
                        // header가 Text-plain을 accept할 수 있으면 아래쪽으로 요청을 넘기겠다.
                        .nest(accept(MediaType.TEXT_PLAIN), b2 -> b2
                                .GET("/", queryParam("name", name -> !name.isBlank()),
                                        GreetingHandler::greetQueryParam)
                                .GET("/name/{name}", GreetingHandler::greetPathVariable)
                                .GET("/header",
                                        headers(h -> h.firstHeader("X-Custom-Name") != null),
                                        GreetingHandler::greetHeader)
                                .POST("/json", contentType(MediaType.APPLICATION_JSON),
                                        GreetingHandler::greetJsonBody)
                                .POST("/text", GreetingHandler::greetPlainTextBody)
                        )
                )
                .build();
    }
}