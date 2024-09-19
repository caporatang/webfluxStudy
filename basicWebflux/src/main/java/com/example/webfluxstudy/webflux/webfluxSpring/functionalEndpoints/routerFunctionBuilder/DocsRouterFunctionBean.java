package com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.routerFunctionBuilder;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.functionalEndpoints.routerFunctionBuilder
 * fileName : DocsRouterFunctionBean
 * author : taeil
 * date : 1/23/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/23/24        taeil                   최초생성
 */
public class DocsRouterFunctionBean {
    // RouterFunction을 bean으로 등록
    // 2개 이상의 RouterFunction이 bean으로 등록되면
    // 1. bean들을 order로 정렬
    // 2. andOther 연산자로 RouterFunction을 합쳐서
    // 3. 합쳐진 RouterFunction으로 ROuterFunctionMapping에서 HandlerFunction 반환

    // -> 여러개의 RouterFunction을 여러개 만들어야하는 상황이라면 order를 잘 설정해서 먼저 처리 하게끔 만들어야한다.
}