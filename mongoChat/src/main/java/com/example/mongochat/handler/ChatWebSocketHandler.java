package com.example.mongochat.handler;

import com.example.mongochat.service.Chat;
import com.example.mongochat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

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
        System.out.println("================= iam : " + iam);
        Flux<Chat> chatFlux = chatService.register(iam);


        chatService.sendChat("system", iam, iam + "님 채팅방에 오신 것을 환영합니다.");

        session.receive()
                .doOnNext(webSocketMessage -> {
                    String payload = webSocketMessage.getPayloadAsText();

                    String[] splits = payload.split(":");
                    String to = splits[0].trim();
                    String message = splits[1].trim();

                    chatService.sendChat(iam, to, message);

                }).subscribe();

        // 채팅방에 오신것을 환영합니다 메세지 누락에 대한 딜레이..
        return session.send(chatFlux.delayElements(Duration.ofMillis(100))
                .map(chat -> session.textMessage(chat.getFrom() + " : " + chat.getMessage())));
    }
}