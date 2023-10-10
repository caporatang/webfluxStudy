package com.example.webfluxstudy.completableFuture.completionStage.thenAsyncSufix;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage
 * fileName : CompletionStageThenAcceptAsyncRunningExample
 * author : taeil
 * date : 2023/10/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/04        taeil                   최초생성
 */
@Slf4j
public class CompletionStageThenAcceptAsyncRunningExample {

    @SneakyThrows
    public static void main(String[] args) {
        // ForkJoinPool 스레드 에서 -> 별도의 스레드에서 동작한다.
        //

        log.info("start main");
        CompletionStage<Integer> stage = Helper.runningStage();

        stage.thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync", i);
        }).thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync", i);
        });
        Thread.sleep(2000);
    }
}