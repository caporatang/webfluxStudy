package com.example.webfluxstudy.completableFuture.completableFuture.anyOf;

import com.example.webfluxstudy.completableFuture.completionStage.thenAsyncSufix.Helper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.anyOf
 * fileName : CompletableFutureAnyOfExample
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
@Slf4j
public class CompletableFutureAnyOfExample {
    public static void main(String[] args) {
        // anyOf는 가장 빨리 반환된 future에 대한 객체를 반환한다

        var startTime = System.currentTimeMillis();
        var firstFuture = Helper.waitAndReturn(100, 1); // 100 미리 세컨즈 대기 후 1 반환
        var secondFuture = Helper.waitAndReturn(500, 2); // 500 미리 세컨즈 대기 후 2 반환
        var thirdFuture = Helper.waitAndReturn(1000, 3);

        CompletableFuture.anyOf(firstFuture, secondFuture, thirdFuture)
                .thenAcceptAsync(v -> {
                    try {
                        log.info("after anyOf");
                        log.info("first value {}", v); // 100미리 세크 후에 오는 1이 가장 빨리 실행되는 future이다. -> value는 1 !
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).join();

        var endTime = System.currentTimeMillis();
        log.info("elapsed : {}ms", endTime - startTime);
    }
}