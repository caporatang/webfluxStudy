package com.example.webfluxstudy.reactor.thread.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static io.netty.handler.codec.mqtt.MqttMessageBuilders.subscribe;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler
 * fileName : ImmediateSchedulerExample
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class ImmediateSchedulerExample {
    // subscribe를 호출한 caller 쓰레드에서 즉시 실행한다.
    // 별도로 Schedulers를 넘기지 않았을 때, Schedulers.immediate를 사용한다.

    public static void main(String[] args) {
        Flux.create(sink -> {
            for (int i = 0; i <= 5; i++) {
                log.info("next : {}", i);
                sink.next(i);
            }
        }).subscribeOn( // subscirbeOn : 해당 ~스케쥴러를 활용해서 진행하겠다.
                // 따로 설정하지 않으면 Immediate와 동일하게 동작한다.
                Schedulers.immediate()
        ).subscribe(value -> {
            log.info("value : " + value);
        });
    }
}