package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completableFuture.isCompletedExceptionally;

import java.util.concurrent.CompletableFuture;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.isCompletedExceptionally
 * fileName : CompletableFutureIsCompletedExceptionallyExample
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
public class CompletableFutureIsCompletedExceptionallyExample {
    public static void main(String[] args) throws InterruptedException {
        var futureWithException = CompletableFuture.supplyAsync(() -> {
            return 1 / 0; // exception !
        });
        Thread.sleep(100);

        assert futureWithException.isDone(); // complete 되었는지 체크
        assert futureWithException.isCompletedExceptionally(); // exception으로 종료 되었는지 체크!
    }
}