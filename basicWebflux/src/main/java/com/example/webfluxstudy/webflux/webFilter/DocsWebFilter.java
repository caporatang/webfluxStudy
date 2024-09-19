package com.example.webfluxstudy.webflux.webFilter;

/**
 * packageName : com.example.webfluxstudy.webflux.webFilter
 * fileName : DocsWebFilter
 * author : taeil
 * date : 1/15/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/15/24        taeil                   최초생성
 */
public class DocsWebFilter {
    // WebFilter
    // WebHandler를 실행하기 앞서 실행.
    // webHandler 에게 전달될 ServerWebExchange와 다음 filter 혹은 hanlder를 실행하기 위한 WebFilterChain
    // chain.filter를 호출하여, 요청을 다음 filter에게 넘기거나 handler를 실행 가능하다.

    // chain.filter를 통해서 다음 filter를 실행하고 Exchange를 건내준다.
    // 더 이상 넘겨줄 filter가 없고, 끝이 났다면 handler.handle(exchange)을 직접 실행한다

    // serverWebExchange와 serverWebExchange 안에 있는 ServerHttpRequest 객체는 변경이 불가능하다
    // ServerWebExchange와 ServerHttpRequest는 각각을 builder로 변경할 수 있는 mutate 제공
    // ServerHttpRequest.Builder 는 method, uri, path, contextPath, header 등.. 변경 가능!
    // ServerWebExchange.Buidler는 request, response, principal 등을 변경 가능하다

    // ServerHttpResponse는 별도의 Builder를 제공하지 않는다 -> afterFilter에서 response 객체를 변경하는건 어렵다

}