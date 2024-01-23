package com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.routerFunctionBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

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

    public static void main(String[] args) throws InterruptedException {
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

        // RouterFunction 실행
        // RotuerFunctions.toHttpHadnelr로 httpHandler로 변경
        // ReactorHandlerAdapter로 HttpServer에 전달
        var httpHandler = RouterFunctions.toHttpHandler(router); // RouterFunctions : 스프링을 쓰지 않더라도 httpHandler를 만들수있게 해주는 클래스
        var adapter = new ReactorHttpHandlerAdapter(httpHandler);

        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();
    }

    // RouterFunction in Spring
    // 총 RouterFunction 이 동작하는 과정
    // 1. ServerWebExchange로 요청이 들어오면, RouterFunction이 bean으로 등록되고 RouterFunctionMapping을 통해서 handlerFunction 반환
    // 2. HandlerFunctionAdapter를 통해서 handlerFunction을 실행하고 ServerResponse를 HandlerResult(ServerResponse)에 담아서 반환한다.
    // 3. ServerResponseResultHandler가 HandlerResult로 render -> writeTo

    // handlerFunction을 이용한 mapping과 adapter
    // RouterFunctionMapping은 HandlerFunction을 반환한다.
    // HandlerFunction은 HandlerFunctionAdapter에서 내부적으로 실행되고
    // HandlerFunction에서 만든 결과인 ServerResponse는 ServerResponseResultHandler로 전달되어 요청이 나가게 된다.
}