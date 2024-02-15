package com.example.webfluxstudy.webflux.webSocket.webSocketHandler.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webSocket.webSocketHandler.example
 * fileName : EchoWebSocketHandler
 * author : taeil
 * date : 2/15/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/15/24        taeil                   최초생성
 */
@Slf4j
public class EchoWebSocketHandler implements WebSocketHandler {
    // 클라이언트로부터 요청을 받으면 그대로 반환하는 webSocketHandler
    // session.receive를 map으로 연결하여 새로운 WebScoketMessage 생성
    // 이를 session.send의 publisher로 client가 요청을 보낼때마다 값을 조금 수정하여 send에게 전달한다.


    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> echoFlux = session.receive()
                // source로 부터 socketMessage를 받음(클라이언트로부터)
                .map(socketMessage -> {
                    String message = socketMessage.getPayloadAsText();
                    log.info("message: {}", message);
                    // session에 textMessage를 호출 -> type 은 text이고 들어온 메세지의 내용을 변경해서 다음 파이프라인으로 내려주는 과정
                    return session.textMessage("ECHO --> " + message);
                });
        // send 메서드는 값을 다시 클라이언트에게 전달하는 역할을 하는 메서드
        return session.send(echoFlux);
    }
}