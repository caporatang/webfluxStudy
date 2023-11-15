package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completionStage.thenAsyncSufix;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage
 * fileName : CompletionStageThenAcceptRunningExample
 * author : taeil
 * date : 2023/10/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/04        taeil                   최초생성
 */
@Slf4j
public class CompletionStageThenAcceptRunningExample {

    @SneakyThrows
    public static void main(String[] args) {
        // 하나의 스레드에서 실행된다.
        // Helper.runningStage 가 다 끝난 후, thenAccept가 실행이 되지만
        // 별도의 스레드에서 동작하는게 아니라 runningStage와 같은 스레드에서 동작한다.

        // done 상태가 아닌 thenAccept는 callee(forkJoinPool)의 쓰레드에서 실행
        // done 상태가 아닌 completionStage에 thenAccept를 사용하는 경우, callee를 block 할 수 있다.

        log.info("start main");
        CompletionStage<Integer> stage = Helper.runningStage();

        stage.thenAccept(i -> {
            log.info("{} in thenAccept, i");
        }).thenAccept(i -> {
            log.info("{} in thenAccept2", i);
        });
        Thread.sleep(2000);
    }
}