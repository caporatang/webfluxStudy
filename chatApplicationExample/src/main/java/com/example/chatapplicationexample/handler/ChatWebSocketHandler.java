package com.example.chatapplicationexample.handler;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.http.WebSocket;
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
@Component
public class ChatWebSocketHandler implements WebSocketHandler {
    @Data
    private static class Chat {
        private final String message;
        private final String from;
    }

    // a는 a대로의 Sink를 가지고 있어야하고, b는 b대로의 sink를 가지고 있어야한다.
    // ConcurrentHashMap : immutable, Thread Safe
    private static Map<String, Sinks.Many<Chat>> chatSinkMap = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        // 연결이되는 순간에 즉, handler를 타는 순간에 Sink에 등록해줘야한다.
        String iam = (String) session.getAttributes().get("iam");
        System.out.println("iam" + iam);
        Sinks.Many<Chat> sink = Sinks.many().unicast().onBackpressureBuffer();

        // a가 채팅을 보내면 a가 sink로 만들어질거고, b가 보내aus b로 만들어질것임.
        chatSinkMap.put(iam, sink);
        sink.tryEmitNext(new Chat(iam + "님 채팅방에 오신 것을 환영합니다.", "System"));

        session.receive()
                .doOnNext(webSocketMessage -> {
                    String payload = webSocketMessage.getPayloadAsText();
                    System.out.println("payload" + payload);
                    // a : 메세지 일때, 보내는 사람과 메세지 내용을 분리
                    String[] splits = payload.split(":");
                    String to = splits[0].trim();
                    String message = splits[1].trim();

                    // a는 b에게, b는 a에게 메세지를 보내야한다. -> 연결이 들어오면 TargetSink에게 값을 넘겨줄것임
                    Sinks.Many<Chat> targetSink = chatSinkMap.get(to);
                    if(targetSink != null) {
                        // 한쪽이 갑자기 연결이 끊기거나, targetSink가 제대로 동작하지 않을경우의 예외처리
                        if(targetSink.currentSubscriberCount() > 0) {
                            // 현재 targetSink를 subscribe하고있는 대상의 카운트를 가져와서 체크
                            targetSink.tryEmitNext(new Chat(message, iam));
                        }

                    } else {
                        sink.tryEmitNext(new Chat("대화 상대가 없습니다.", "system"));
                    }

                }).subscribe();

        // Sink는 스트링을 반환하는데, send는 웹소켓메세지가 필요하기 때문에 바꿔주자
        return session.send(sink.asFlux()
                .map(chat -> session.textMessage(chat.from + " : " + chat.message)));
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