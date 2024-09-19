package com.example.webfluxstudy.webflux.webHandler;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler
 * fileName : DocsWebHandler
 * author : taeil
 * date : 1/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/8/24        taeil                   최초생성
 */
public class DocsWebHandler {
    // HttpHandler의 단점을 개선한 컴포넌트
    // Fitler, Codec, ExceptionHandler 등을 지원한다.
    // ServerWebExchange를 제공한다.

    // ServerWebExchange interface
    // getRequest : ServerHttpRequest 제공
    // getResponse : ServerHttpResponse 제공
    // getAttributes : 요청 처리 중에 추가하거나 변경할 수 있는 key-value로 구성된 Map제공 -> 상태를 전달하거나 공유하는데 사용
    // getSession : getSession : Session 정보를 담고 있는 WebSessions publisher를 반환
    // getPrincipal : Security 정보를 담고 있는 Principal publisher를 반환한다.
    // getFromData : Content-Type이 application/x-www-form-urlencoded인 경우, MultiValueMap 를 담고있는 publisher 제공
    // getMultipartData : Content-Type이 multipart/form-data인 경우, body를 MultiValueMap 형태로 제공
    // getApplicationContext : Spring 환경에서 구동된 경우 applicationContext 반환 -> Spring 환경이 아니라면 null
}