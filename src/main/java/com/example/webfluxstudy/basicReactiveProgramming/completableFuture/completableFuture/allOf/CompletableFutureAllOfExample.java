package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completableFuture.allOf;

import com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completionStage.thenAsyncSufix.Helper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.allOf
 * fileName : CompletableFutureAllOfExample
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
@Slf4j
public class CompletableFutureAllOfExample {
    public static void main(String[] args) {
        var startTime = System.currentTimeMillis();
        var firstFuture = Helper.waitAndReturn(100, 1); // 100 미리 세컨즈 대기 후 1 반환
        var secondFuture = Helper.waitAndReturn(500, 2); // 500 미리 세컨즈 대기 후 2 반환
        var thirdFuture = Helper.waitAndReturn(1000, 3);

        CompletableFuture.allOf(firstFuture, secondFuture, thirdFuture)
                .thenAcceptAsync(v -> {
                    // 3개의 future가 모두 끝날때까지 대기. -> 한 지점에서 출력.
                    log.info("after allOf");
                    try {
                        log.info("first: {}", firstFuture.get());
                        log.info("second: {}", secondFuture.get());
                        log.info("third: {}", thirdFuture.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).join();

        var endTime = System.currentTimeMillis();
        log.info("endTime {} ", endTime);
    }
}