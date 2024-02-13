package com.example.webfluxstudy.serverSentEvent.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * packageName : com.example.webfluxstudy.serverSentEvent.service
 * fileName : NotificationService
 * author : taeil
 * date : 2/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/13/24        taeil                   최초생성
 */
@Slf4j
@Service
public class NotificationService {
    // 다른 bean 에서, 컨트롤러 밖에서 Sink를 사용하려면?
    // 결국! 외부에서 sink를 사용할때도 마찬가지다. 그냥 호출해서 사용하면 된다
    private static Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    public Flux<String> getmeesageFromSink() {
        return sink.asFlux();
    }

    public void tryEmitNext(String message) {
        log.info("message : {}", message);
        sink.tryEmitNext(message);

    }

}