package com.example.webfluxstudy.completableFuture.completableFuture.supplyAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.supplyAsync
 * fileName : CompletableFutureSupplyAsyncExample
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
public class CompletableFutureSupplyAsyncExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        });
        // SupplyAsync는 Non-blocking으로 동작하기 때문에 해당 시점에서는 Done 상태가 아님
        assert !future.isDone(); // -> false

        // 시간이 지나면서 1이 반환됐을거고, 아래 코드는 true가 반환된다.
        Thread.sleep(1000);

        assert future.isDone();
        assert future.get() == 1;
    }
}