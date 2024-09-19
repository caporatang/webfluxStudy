package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completionStage.thenAsyncSufix;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage
 * fileName : CompletionStageThenAcceptExample
 * author : taeil
 * date : 2023/10/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/04        taeil                   최초생성
 */
@Slf4j
public class CompletionStageThenAcceptExample {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        // stage = 1
        CompletionStage<Integer> stage = Helper.finishedStage();

        stage.thenAccept(i -> {
            // i  = 1
            log.info("{} in then Accept", i);
        }).thenAccept(i -> {
            // thenAccept는 반환값이 없기 떄문에 i = null
            log.info("{} in thenAccept2", i);
        });
        log.info("after thenAccept");
        Thread.sleep(100);
    }
}