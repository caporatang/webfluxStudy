package com.example.webfluxstudy.serverSentEvent.controller;

import com.example.webfluxstudy.serverSentEvent.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * packageName : com.example.webfluxstudy.serverSentEvent.controller
 * fileName : NotificationController
 * author : taeil
 * date : 2/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/13/24        taeil                   최초생성
 */
@Slf4j
@RequestMapping("/api/notifications")
@RestController
public class NotificationController {

    // Sink ?
    // sink에 특정 값들을 저장할 수 있는데 Flux로 변환해서 흘려줄수있게 해준다.
    // sink 의 many는 Flux -> 싱글 subscribe 처리
    private static Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    private static AtomicInteger lastEventId = new AtomicInteger(1);

    // ServerSendEvent 에서는 반드시 produces를 event value로 명시 해주어야한다.
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> getNotifications() {
        return sink.asFlux()
                .map(message -> {
                    String id = lastEventId.getAndIncrement() + "";
                    return ServerSentEvent
                            .builder(message)
                            .event("notification")
                            .id(id)
                            .comment("this is notification")
                            .build();
                });
        // 간단하게 얘기해서 요청에서 받은 값을 sink에 밀어넣고, 밀어넣은 값을 꺼내서 sink를 Flux로 만들어서 return한것임
    }


    @PostMapping
    public Mono<String> addNotification(@RequestBody Event event) {
        String newNotificationMessage = event.getType() + " : " + event.getMessage();
        log.info("message : {}", newNotificationMessage);
        sink.tryEmitNext(newNotificationMessage);

        return Mono.just("ok");

    }

}