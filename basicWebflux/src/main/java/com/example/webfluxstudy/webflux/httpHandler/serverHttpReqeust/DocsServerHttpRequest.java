package com.example.webfluxstudy.webflux.httpHandler.serverHttpReqeust;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.RequestPath;

import java.net.URI;

/**
 * packageName : com.example.webfluxstudy.webflux.httpHandler.serverHttpReqeust
 * fileName : DocsServerHttpRequest
 * author : taeil
 * date : 1/3/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/3/24        taeil                   최초생성
 */
@Slf4j
public class DocsServerHttpRequest {
    // 제공되는 메서드
    // getPath : query를 포함하지 않는 구조화된 path 정보 제공
    // getQueryParams : decoded된 query parameter map
    // getCookies : 클라이언트가 전달한 읽기만 가능한 쿠키 map 제공
    // mutate : uri, path, header 등 을 변경할 수 있는 ServerHttpRequest builder 제공
    // getBody : 클라이언트가 전달한 request body를 Flux<DataBuffer> 형태로 수신. Flux이기 때문에 여러 개의 DataBuffer 전달 가능

    // getHeaders : HttpHeaders 객체에 접근 가능. HttpHeaders에서 header 추가, 수정, 삭제 기능 제공
    // getMethod : http 요청 method
    // getURI : query string 을 포함한 uri 정보를 제공

    // getPath와 getURI의 차이는?
    // RequestPath
    // RequestPath와 application 내부의 path를 분리해서 제공한다.
    // spring webflux에선 기본적으로 root context path("/")를 갖는다.
    // spring.webflux.base-path property를 제공하여 변경할 수 있음
    @SneakyThrows
    public static void main(String[] args) {
        URI uri = new URI("http://localhost:8080/app/api/hello?name=taeil#home");
        RequestPath path = RequestPath.parse(uri, "/app");

        log.info("path.pathWithinApplication : {}", path.pathWithinApplication()); // /api/hello
        log.info("path.contextPath : {}", path.contextPath()); // /app
    }

}