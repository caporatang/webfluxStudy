package com.example.webfluxstudy.webflux.serverSentEvent.controller;

import com.example.webfluxstudy.webflux.serverSentEvent.domain.Event;
import com.example.webfluxstudy.webflux.serverSentEvent.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
@RequiredArgsConstructor
public class NotificationController {

    // Sink ?
    // sink에 특정 값들을 저장할 수 있는데 Flux로 변환해서 흘려줄수있게 해준다.
    // sink 의 many는 Flux -> 싱글 subscribe 처리
    // private static Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    private final NotificationService notificationService;
    private static AtomicInteger lastEventId = new AtomicInteger(1);

    // ServerSendEvent 에서는 반드시 produces를 event value로 명시 해주어야한다.
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> getNotifications() {
        return notificationService.getmeesageFromSink()
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
        // 결국! 외부에서 sink를 사용할때도 마찬가지다. 그냥 호출해서 사용하면 된다
        String message = event.getType() + " : " + event.getMessage();
        notificationService.tryEmitNext(message);
        return Mono.just("ok");
    }

}