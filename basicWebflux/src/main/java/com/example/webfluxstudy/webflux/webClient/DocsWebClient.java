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


    // WebClient 제공 method
    // 1. get, head, post, put, patch, delete, options, method 제공
    // 2. post, put, patch 의 경우 -> RequestBodyUriSpec을, 나머지는 RequestHeadersUriSpec을 반환한다.

    // RequestBodySpec 란? : RequestBodySpec 과 UriSpec이 합쳐짐
    // 1. reqeust의 body를 설정할 수 있는 메소드 제공 : contentLength, contentType, bodyValue
    // 2. body 호출시 RequestHeaderSpec 반환
    // 3. bodyValue : 객체를 받아서 내부적으로 codec을 이용하여 변환
    // 4. Publisher를 이용해서 변환하거나 BodyInserter를 이용해서 변환 가능

    // ReqeustHeadersSpec
    // 1. accept, acceptCharset : Accept 헤더 변경
    // 2. cooke, cookies : 요청의 쿠키 변경
    // 3. header, headers : 요청의 header 변경
    // 4. attribute : webClient의 filter들에 제공되는 attribute 설정
    // 5. httpRequest : HTTP 라이브러리의 요청에 직접 접근할 수 있게 callback 제공

    // 6. retrieve, exchangeToMono, exchangeToFlux를 실행하면 요청을 서버에 전달하고 응답을 받는다.
    // retrieve : ResponseSpec을 반환
    // exchangeToMono : ClientResponse를 인자로 받고 body를 Mono 형태로 반환하는 callback
    // exchangeToFlux : ClientResponse를 인자로 받고 body를 Flux 형태로 반환하는 callback

    // UriSpec
    // uri를 변경할 수 있는 Spec
    // RequestHeadersUriSpec 과 RequestBodyUriSpec이 이를 구현한다.

    // ResponseSpec
    // onStatus : response를 확인하고 문제가 발생한 경우 Mono.error를 반환하여 error 전파
    // bodyToMono, bodyToFlux : response를 mono나 flux로 변환
    // toEntity, toEntityList, toEntityFlux : response를 ResponseEntity로 반환하고 각각의 body를 하나의 객체 혹은 List나 Flux로 변환
    // toBodilessEntity : response를 ResponseEntity로 변환하고 body는 포함하지 않는다.

}