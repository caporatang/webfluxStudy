package com.example.webfluxstudy.serverSentEvent.service;

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
@Service
public class NotificationService {
    // 다른 bean 에서, 컨트롤러 밖에서 Sink를 사용하려면?
    private static Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    public Flux<String> getmeesageFromSink() {
        return null;
    }

}