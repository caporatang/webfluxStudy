package com.example.webfluxstudy.webflux.webSocket.webSocketHandler;

/**
 * packageName : com.example.webfluxstudy.webflux.webSocket.webSocketHandler
 * fileName : DocsWebSocketHandler
 * author : taeil
 * date : 2/15/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/15/24        taeil                   최초생성
 */
public class DocsWebSocketHandler {
    // simpleUrlHandlerMapping이  webSocketHandler를 반환하고 handlerAdapter에서 webScocketHandler를 이용해서 webScoket을 제공하는 형태..

    // handle을 통해서 WebSocketSession을 받고 Mono<Void> 를 반환한다.
    // 인자로 받는 부분만 제외하면 WebHandler와 비슷하다.
    // webSocketSession은 양방향을 지원한다.

    // webSocketSession 제공하는 메서드
    // bufferFactory : 쉽게 DataBuffer로 변경 가능
    // getAttributes : 쉽게 attribute에 접근하고 수정 가능
    // receive : source로부터 Flux의 형태로 WebSocketMessage를 받는다. -> 여기서 source는 클라이언트를 의미한다.
    // send : WebSocketMessage publisher를 이용해서 WebSocketMessage를 전달한다. -> 서버입장에서 보내는쪽의 destination은 클라이언트를 의미한다.
    // isOpen : WebSocket이 열려있는지 확인한다.
    // close : NORMAL 상태로 WebSocket을 닫는다. -> 혹은 특정 status로 닫는것도 가능하다.

    // CloseStatus 의 종류 -> 빈번하게 사용되는 status
    // 1000 : NORMAL 정상적으로 webSocket이 종료
    // 1001 : GOING_AWAY 서버가 예상치 못하게 종료되거나 페이지에서 벗어난 경우
    // 1002 : PROTOCOL_ERROR 프로토콜에 문제가 있는 경우
    // 1003 : NOT_ACCEPTABLE accept할 수 없는 데이터를 받은 경우
    // 1009 : TOO_BIG_TO_PROCESS 처리하기에 너무 큰 데이터를 받은 경우
    // 1011 : SERVER_ERROR 예상하지 못한 에러로 서버에서 요청을 처리하지 못하는 경우
    // 1012 : SERVICE_RESTARTED 서비스가 재시작. 클라이언트는 5~30초 사이로 랜덤하게 접근.


    // 양방향 통신을 지원하기 때문에 밖으로 내보내는 factory method도 지원한다.
    // textMessage : 주어진 payload를 bufferFactory를 이용하여 WebSocketMessage로 변경
    // binaryMessage : 주어진 binary를 bufferFactory를 이용하여 WebSocketMessage로 변경
    // pingMessage, pongMessage : 서버나 클라이언트가 pingMessage를 전달하면 반대쪽은 pongMessage를 반환해야 한다.
}