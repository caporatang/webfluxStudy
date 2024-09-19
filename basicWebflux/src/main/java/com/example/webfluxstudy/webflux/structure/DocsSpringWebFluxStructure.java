package com.example.webfluxstudy.webflux.structure;

/**
 * packageName : com.example.webfluxstudy.webflux
 * fileName : DocsSpringWebFlux
 * author : taeil
 * date : 1/3/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/3/24        taeil                   최초생성
 */
public class DocsSpringWebFluxStructure {
    // 스프링 웹플럭스는 어떻게 구성되어 있는가 ?

    // WebFluxAutoConfiguration은 ReactiveWebServerFactoryConfiguration에 의존한다.

    // ReactiveWebServerFactoryConfiguration는 4가지 클래스를 제공한다.
    // 1. EmbeddedTomcat.class
    // 2. EmbeddedJetty.class
    // 3. EmbeddedUndertow.class
    // 4. EmbeddedNetty.class

    // EmbeddedNetty는 NettyReactiveWebServerFactory를 가지고 있다.
    // NettyReactiveWebServerFactory 는 내부적으로 Http 서버는 reactor netty에서 제공하는 HTTP 서버를 사용한다.


}