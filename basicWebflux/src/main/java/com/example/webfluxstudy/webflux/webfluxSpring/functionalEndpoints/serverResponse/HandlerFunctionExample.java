package com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.serverResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseCookie;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.serverResponse
 * fileName : HandlerFunctionExample
 * author : taeil
 * date : 1/23/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/23/24        taeil                   최초생성
 */
@Slf4j
public class HandlerFunctionExample {
    // HandlerFunction은 ..~
    // 풍부한 기능을 가지고 있는 ServerRequest -> 이 request 만으로 대부분의 일을 할수있음
    // ServerResponseBuilder로 쉽게 응답을 생성가능
    // 함수형 인터페이스로 간단하게 HandlerFunction 생성 가능

    // HandlerFunction을 path와 연결하려면 어떻게 해야할까 ?

    public static void main(String[] args) {
        HandlerFunction<ServerResponse> handler = request -> {
            // ServerRequest를 인자로 받고 queryParam으로 name 획득
            String name = request.queryParam("name")
                    .orElse("world");

            String content = "Hello, " + name;
            ResponseCookie cookie = ResponseCookie.from("name", name).build();

            // serverResponse의 활용
            // ServerResponse.ok() 로 ok status 를 갖는 BodyBuidler를 생성
            // headers, cookie, cacheControl 설정
            // bodyValue로 content를 받고 그 후 build
            return ServerResponse.ok()
                    .cookie(cookie)
                    .headers(headers -> headers.add("X-Hello", name))
                    .cacheControl(CacheControl.noCache())
                    .bodyValue(content);
        } ;
    }
}