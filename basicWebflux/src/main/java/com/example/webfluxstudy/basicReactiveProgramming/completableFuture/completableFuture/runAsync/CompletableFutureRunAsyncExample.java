package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completableFuture.runAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.runAsync
 * fileName : CompletableFutureRunAsyncExample
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
public class CompletableFutureRunAsyncExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        // 현재 시점에는 isDone이 false
        assert !future.isDone();

        // 1초가 지나면서 isDone이 true로 반환
        Thread.sleep(1000);
        assert future.isDone();
        assert future.get() == null; // runAsync의 반환값은 null !
    }
}