package com.example.webfluxstudy.webflux.webClient;

/**
 * packageName : com.example.webfluxstudy.webflux.webClient
 * fileName : DocsWebClient
 * author : taeil
 * date : 1/29/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/29/24        taeil                   최초생성
 */
public class DocsWebClient {
    // RestTemplate은 이전부터 사용되었던 동기 blocking 기반의 webClient
    // Spring 5.0부터 더 이상 개발도지ㅣ 않고 유지 모드 유지중이다. -> webClient 사용을 권장하고 있다.

    // WebClient
    // Non-blocking reactive http 클라이언트
    // Reactor Netty, Jetty, apache의 HttpComponent를 이용해서 구현한다.
    // http 호출을 위한 여러 설정들을 메소드 체이닝을 통해서 설정한

    // WebClient 를 구현한 구현체로 DefaultWebClient 가 있고
    // DefaultWebClient 는 ExchangeFunction을 가지고 있고,
    // ExchangeFunction은 ClientHttpConnector를 가지고 있다.
    // ClientHttpConnector는 Reactor, Jetty, HttpComponents 3가지의 adapter를 가지고 있다.
}