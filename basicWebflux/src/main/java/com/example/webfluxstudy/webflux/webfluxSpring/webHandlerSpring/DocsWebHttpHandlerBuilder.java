package com.example.webfluxstudy.webflux.webfluxSpring.webHandlerSpring;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring
 * fileName : DocsWebHttpHandlerBuilder
 * author : taeil
 * date : 1/21/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/21/24        taeil                   최초생성
 */
public class DocsWebHttpHandlerBuilder {
    // WebHandlerAdapter등을 매번 작성하기가 번거롭다.. -> 스프링에서는 어떻게 지원하는지 알아보자..~

    // HttpHandlerAutoCOnfiguration
    // -> Spring 환경에서는 applicationContext를 이용하여 WebHttpHandlerBuilder를 build해서 사용한다. -> WebHttpHandlerBuilder.applicationContext

    // applicationContext로부터 "webHandler" 이름을 갖는 bean을 찾아서 webHandler로 등록한다.
    // applicationCOntext로부터 WebFilter class를 갖는 bean 목록을 찾고 order로 sort하여 filters로 등록한다.
    // applicationCOntext로부터 WebExceptionHandler class를 갖는 bean 목록을 찾고 order로 sort 하여 exceptionHandlers로 등록한다.

    // WebHttpHandlerBuilder 는 이름이나, 타입을 통해 등록되어 있는 bena을 찾는다
    // -> WebSessionManager, ServerCodecCOnfigurer, LocaleContextResolver, ForwardedHeaderTransformer 등 bean을 찾아서 builder에 등록한다


}