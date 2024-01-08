package com.example.webfluxstudy.webflux.webHandler;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler
 * fileName : DocsWebHttpHandlerAdapter
 * author : taeil
 * date : 1/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/8/24        taeil                   최초생성
 */
public class DocsWebHttpHandlerAdapter {
    // Builder에 주어진 Filters, ExceptionHandlers, CodecConfigurer, ApplicationContext 등을 이용하여 하나의 HttpWebHandlerAdapter를 생성한다.

    // HttpWebHandlerAdapter 는 HttpHandler를 구현한다.
    // public class HttpWebHandlerAdapter extends WebHandlerDecorator implements HttpHandler

    // HttpWebHandlerAdapter 의 간단한 처리과정
    // handle 메서드를 통해서 요청 응답을 처리하고, 요청이 들어오면 ServerWebExchange를 생성한다.
    // serverWebExchange를 webFilter로 넘김. -> webFilter에서 받아서 필터링.. -> 여러가지 값을 추가하거나 삭제하거나 튕겨내거나
    // 필터링작업이 끝나면 WebHandler로 요청을 넘김 -> webHandler는 쿠키 바디 헤더 등 .. 값에 접근해서 가공한 후 body에 write을 통해서 값을 담아서 내보냄
    // 이 때 , 익셉션이 발생한다면 WebExceptionHandler로 넘어가게 된다.
    // WebExceptionHandler 에서도 쓰로우하게 된다면 handleUnresolvedError로 넘어가고 그대로 반환된다.

    // 1. handle 메서드 받음 -> ServerWebExchange 객체 생성
    // 2. WebFilter에서 ServerWebExchange 받음 -> 필터링 작업
    // 3. WebHandler로 필터링 작업이 끝난 요청을 넘김 -> 데이터 가공 후 리턴
    // 4. WebHandler 에서 문제가 생겼다면 WebExceptionHandler로 전달
    // 5. WebExceptionHandler 에서 아무 ExceptionHandle 작업이 없었다면
    // 6. handleUnresolvedError로 Excpetion 전달 및 응답 반환
}