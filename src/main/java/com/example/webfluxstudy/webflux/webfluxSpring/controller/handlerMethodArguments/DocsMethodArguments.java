package com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments
 * fileName : DocsMethodArguments
 * author : taeil
 * date : 1/25/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/25/24        taeil                   최초생성
 */
public class DocsMethodArguments {
    // Servlet stack 의 Controller method argument와 거의 비슷
    // 하지만 servlet stack 에서 제공되지 않고 reactive stack에서만 제공되거나 같은 역할을 하지만 다른 객체들이 제공된다.

    // 1. ServerWebExchange : method argument로 ServerWebExchange 접근 가능
    // 2. ServerHttpReqeust / Response : method argument로 ServerHttpRequest, ServerHttpResponse접근 가능

    // 3. WebSession : servlet stack의 HttpSession과 비슷하다.
    // 3-1 새로운 attribute가 추가되기 전까지 새로운 session을 강제하지 않는다.
    // 3-2 HttpSession은 session을 강제로 생성해서 절대 Null이 될 수 없다.
    // 3-3 reactive type을 지원한다.
    // 3-4 재요청시 Session이 Cookie로 전달되고 다른 name을 전달해도 session의 값을 사용해서 응답을 반환한다.

    
}