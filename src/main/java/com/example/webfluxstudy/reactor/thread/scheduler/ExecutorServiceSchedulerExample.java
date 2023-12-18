package com.example.webfluxstudy.reactor.thread.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler
 * fileName : ExecutorServiceSchedulerExample
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
@Slf4j
public class ExecutorServiceSchedulerExample {

    // 이미 존재하는 ExecutorService로 Scheduler 생성
    @SneakyThrows
    public static void main(String[] args) {
        var executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            Flux.create(sink -> {
                log.info("next : {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    Schedulers.fromExecutorService(executorService)
            ).subscribe(value -> {
                log.info(" value : " + value);
            });
        }
        Thread.sleep(1000);
        executorService.shutdown();
    }
}