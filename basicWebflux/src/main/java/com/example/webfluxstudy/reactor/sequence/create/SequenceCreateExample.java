package com.example.webfluxstudy.reactor.sequence.create;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence.create
 * fileName : SequenceCreateExample
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
@Slf4j
public class SequenceCreateExample {
    // create
    // 2개의 쓰레드에서 sink, next를 수행한다.
    // CompletableFuture 의 allOf를 활용하여 두개의 작업이 끝난 후 complete 이벤트 전달

    // generate보다는 create를 사용하고 for문을 사용해서 next를 활용하고 끝났을때 complete 이벤트를 호출하는게 권장사항이다..!
    @SneakyThrows
    public static void main(String[] args) {
        Flux.create(sink -> {
            var task1 = CompletableFuture.runAsync(() -> {
                for (int i = 0; i < 5; i++) {
                    sink.next(i);
                }
            });
            var task2 = CompletableFuture.runAsync(() -> {
                for (int i = 5; i < 10; i++) {
                    sink.next(i);
                }
            });
            CompletableFuture.allOf(task1, task2)
                    .thenRun(sink::complete);
        }).subscribe(value -> {
            log.info("value : " + value);
        }, error -> {
            log.error("error: " + error);
        }, () -> {
            log.info("complete");
        });
        Thread.sleep(1000);
    }
}