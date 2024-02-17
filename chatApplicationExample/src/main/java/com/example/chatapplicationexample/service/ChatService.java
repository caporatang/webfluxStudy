package com.example.chatapplicationexample.service;

import com.example.chatapplicationexample.Chat;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName : com.example.chatapplicationexample.service
 * fileName : ChatService
 * author : taeil
 * date : 2/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/17/24        taeil                   최초생성
 */
@Service
public class ChatService {
    // 핸들러에 있던 Sink에 등록하고 반환하는 로직을 분리

    private static Map<String, Sinks.Many<Chat>> chatSinkMap = new ConcurrentHashMap<>();

    public Flux<Chat> register(String iam) {
        // 단체 채팅방을 구현한다고 가정하면, unicast가 아니라 Multicast로 사용하면 될거같다.
        Sinks.Many<Chat> sink = Sinks.many().unicast().onBackpressureBuffer();
        chatSinkMap.put(iam, sink);

        return sink.asFlux();
    }

    public Flux<Chat> getChatFlux(String iam) {
        // null이 아니라고 가정하자..체크는 해야겠지만.
        Sinks.Many<Chat> sink = chatSinkMap.get(iam);
        return sink.asFlux();
    }


    public boolean sendChat(String iam, Chat chat) {
        Sinks.Many<Chat> sink = chatSinkMap.get(iam);

        if(sink == null) return false;
        sink.tryEmitNext(chat);

        return true;
    }
}