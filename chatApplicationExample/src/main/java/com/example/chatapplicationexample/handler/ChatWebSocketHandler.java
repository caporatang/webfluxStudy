package com.example.chatapplicationexample.handler;

import com.example.chatapplicationexample.Chat;
import com.example.chatapplicationexample.service.ChatService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.http.WebSocket;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName : com.example.chatapplicationexample.handler
 * fileName : ChatWebSocketHandler
 * author : taeil
 * date : 2/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/17/24        taeil                   최초생성
 */
@RequiredArgsConstructor
@Component
public class ChatWebSocketHandler implements WebSocketHandler {
    private final ChatService chatService;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String iam = (String) session.getAttributes().get("iam");
        Flux<Chat> chatFlux = chatService.register(iam);

        // chatService.register(iam);
        chatService.sendChat(iam, new Chat(iam + "님 채팅방에 오신것을 환영합니다.", "system"));

        session.receive()
                .doOnNext(webSocketMessage -> {
                    String payload = webSocketMessage.getPayloadAsText();

                    String[] splits = payload.split(":");
                    String to = splits[0].trim();
                    String message = splits[1].trim();

                    boolean result = chatService.sendChat(to, new Chat(message, iam));

                    if(!result) {
                        chatService.sendChat(iam, new Chat("대화 상대가 없습니다.", "system"));
                    }

                }).subscribe();

        // 채팅방에 오신것을 환영합니다 메세지 누락에 대한 딜레이..
        return session.send(chatFlux.delayElements(Duration.ofMillis(100))
                .map(chat -> session.textMessage(chat.getFrom() + " : " + chat.getMessage())));
    }


    //@Override
    //public Mono<Void> handle(WebSocketSession session) {
    //    // ########### echo 서버에서는 이렇게 구현하면 된다. ###########
    //    Flux<WebSocketMessage> messageFlux = session.receive()
    //            .map(webSocketMessage -> {
    //                String message = webSocketMessage.getPayloadAsText();
//
    //                return session.textMessage(message);
    //            });
    //    return session.send(messageFlux);
//
    //}
}