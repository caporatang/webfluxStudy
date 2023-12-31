package com.example.webfluxstudy.reactor.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * packageName : com.example.webfluxstudy.reactor.context
 * fileName : ThreadLocal
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
@Slf4j
public class UselessThreadLocalExample {
    // 하나의 쓰레드에 값을 저장하고 해당 쓰레드 내에서 어디서든지 접근 가능하다.
    // 만약 subscribeOn, publishOn으로 실행 쓰레드가 달라진다면?

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("woman"); // -> 쓰레드 로컬에 저장 -> 메인 쓰레드에 저장

        Flux.create(sink -> {
            // 메인 쓰레드에서 저장한 값을 다른 스레드에서 호출해서 불러올려고 하니 접근할 수 없어서 다 null이다.
            log.info("threadLocal: " + threadLocal.get());
            sink.next(1);
        }).publishOn(Schedulers.parallel()
        ).map(value -> {
            log.info("threadLocal: " + threadLocal.get());
            return value;
        }).publishOn(Schedulers.boundedElastic()
        ).map(value -> {
            log.info("threadLocal: " + threadLocal.get());
            return value;
        }).subscribeOn(Schedulers.single()
        ).subscribe();

        Thread.sleep(1000);
    }
}