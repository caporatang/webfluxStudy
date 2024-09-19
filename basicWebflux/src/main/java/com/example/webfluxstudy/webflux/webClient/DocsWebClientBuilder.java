package com.example.webfluxstudy.webflux.webClient;

/**
 * packageName : com.example.webfluxstudy.webflux.webClient
 * fileName : DocsWebClientBuilder
 * author : taeil
 * date : 1/29/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/29/24        taeil                   최초생성
 */
public class DocsWebClientBuilder {
    // WebClient를 만들면서 필요한 설정들을 제공한다.
    // baseUrl : webClient의 기본 url. 모든 요청의 path에 prefix로 들어간다.
    // defaultHeaders : 기본으로 제공되는 헤더 변경
    // defaultCookies : 기본으로 제공되는 쿠키 변경
    // filters : WebHandler와 WebFilter처럼 webClient가 요청을 보내기 전후에 동작하는 Filter
    // clientConnector : webClient에서 사용되는 clientConnecotor 변경 -> 라이브러리도 직접 주입 가능하다. -> 기본적으로 제공되는 커넥터를 꼭 사용할 필요없음
    // codecs : client에의 CodecConfigurer 설정
    // exchangeFunction : http 요청을 수행하는 exchangeFunction을 변경
}