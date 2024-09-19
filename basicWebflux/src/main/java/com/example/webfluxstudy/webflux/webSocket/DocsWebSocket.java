package com.example.webfluxstudy.webflux.webSocket;

/**
 * packageName : com.example.webfluxstudy.webflux.webSocket
 * fileName : DocsWebSocket
 * author : taeil
 * date : 2/15/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/15/24        taeil                   최초생성
 */
public class DocsWebSocket {
    // OSI 7계층에 위치하는 프로토콜 - 4계층의 TCP에 의존한다.
    // 하나의 TCP 연결로 실시간 양방향 통신을 지원한다.
    // HTTP straming에서 서버에서 클라이언트로만 이벤트를 전달했지만 WebSocket은 양방향 통시닝 가능하다.
    // HTTP와 달리 지속적인 연결을 유지하면 오버헤드가 적다

    // 요청과 응답
    // 요청에서 Upgrade : websocket과 Connection : Upgrade 헤더를 추가
    // 응답으로 status 101과 함께 Upgrade : webSocket, Connection : Upgrade 헤더를 제공한다.
    // Http streaming의 단점인 단방향 통신을 보완할수있음

    // HandlerMapping
    // 대표적인 HandlerMapping
    // 1. RequestMappingHandlerMapping
    // 2. RouterFunctionMapping
    // 3. SimpleUrlHandlerMapping

    // 반환하는 hnalder Type
    // 1. HandlerMethod
    // 2. HandlerFunction
    // 3. WebHandler, WebSocketHandler

    // WebSocket을 이용한 Mapping과 Adapter
    // ->  SimpleUrlHandlerMapping 에서 WebSocketHandler를 반환하고, WebSocketHandler를 WebSocketHandlerAdapter에서 받아서 HandlerAdapter에서 처리한다.
}