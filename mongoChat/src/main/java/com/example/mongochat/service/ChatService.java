package com.example.mongochat.service;

import com.example.mongochat.entity.ChatDocument;
import com.example.mongochat.repository.ChatMongoRepository;
import com.mongodb.client.model.changestream.OperationType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonValue;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
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
@RequiredArgsConstructor
@Service
@Slf4j
public class ChatService {
    // 핸들러에 있던 Sink에 등록하고 반환하는 로직을 분리

    private final ChatMongoRepository chatMongoRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private static Map<String, Sinks.Many<Chat>> chatSinkMap = new ConcurrentHashMap<>();

    public Flux<Chat> register(String iam) {
        // 단체 채팅방을 구현한다고 가정하면, unicast가 아니라 Multicast로 사용하면 될거같다.
        Sinks.Many<Chat> sink = Sinks.many().unicast().onBackpressureBuffer();
        chatSinkMap.put(iam, sink);

        return sink.asFlux();
    }

    @PostConstruct
    public void setup() {
        reactiveMongoTemplate.changeStream(ChatDocument.class)
        .listen()
        .doOnNext(item -> {
            ChatDocument target = item.getBody();
            OperationType operationType = item.getOperationType();

            log.info("target: {}", target);
            log.info("type: {}", operationType);

            if(target != null && operationType == OperationType.INSERT) {
                String from = target.getFrom();
                String to = target.getTo();
                String message = target.getMessage();
                doSend(from, to, message);
            }
        }).subscribe();
    }

    public Flux<Chat> getChatFlux(String iam) {
        // null이 아니라고 가정하자..체크는 해야겠지만.
        Sinks.Many<Chat> sink = chatSinkMap.get(iam);
        return sink.asFlux();
    }


    public void sendChat(String from, String to, String message) {
        log.info("from: {}, to: {}, message: {}", from, to, message);
        // doSend(from, to, message);

        var documentToSave = new ChatDocument(from, to, message);
        chatMongoRepository.save(documentToSave)
                .subscribe();
    }


    public void doSend(String from, String to, String message) {
        Sinks.Many<Chat> sink = chatSinkMap.get(to);

        if (sink == null) {
            Sinks.Many<Chat> my = chatSinkMap.get(from);
            my.tryEmitNext(new Chat("대화 상대가 없습니다.", "system"));
            return;
        }
        sink.tryEmitNext(new Chat(message, from));
    }
}