package com.example.webfluxstudy.completableFuture.completionStage;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage
 * fileName : Helper
 * author : taeil
 * date : 2023/10/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/04        taeil                   최초생성
 */
@Slf4j
public class Helper {
    // ----------------- CompletionStage 연산자 -----------------
    // thenAccept[Async] : Consumer를 파라미터로 받는다, 이전 task로부터 값을 받지만 값을 넘기지 않는다, 다음 task에게 null이 전달된다. 값을 받아서 action만 수행하는 경우 유용하다.

    @SneakyThrows
    public static CompletionStage<Integer> finishedStage() {
        // 1을 반환하는 완료된 CompletableFuture 반환
        var future = CompletableFuture.supplyAsync(() -> {
            log.info("supplyAsync");
            return 1;
        });
        Thread.sleep(100);
        return future;
    }

    public static CompletionStage<Integer> runningStage() {
        // runningStage : 1초를 sleep한 후 1을 반환하는 completableFuture
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                log.info("I'm running!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        });
    }

}