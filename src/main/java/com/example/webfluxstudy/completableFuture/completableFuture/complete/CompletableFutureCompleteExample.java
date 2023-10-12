package com.example.webfluxstudy.completableFuture.completableFuture.complete;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.complete
 * fileName : CompletableFutureCompleteExample
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
public class CompletableFutureCompleteExample {
    // Future를 가지고 있는 함수가 완료되지 않은 상태라면 바로 complete 시킬 수 있음.
    // 이미 complete가 되었다면 complete가 된 시점에 있는 값을 유지
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        assert !future.isDone();

        var triggered = future.complete(1); // Future에 1을 채우고 complete 시킴
        assert future.isDone(); // complete 시켜서 곧 바로 완료.
        assert triggered; // true
        assert future.get() == 1; // complete에서 1을 할당했기 떄문에 1을 가지고 있음

        triggered = future.complete(2); // 2로 complete 시도
        assert future.isDone(); // 위에서 완료 시켰기 때문에 true
        assert !triggered; // value 2로 complete 시킨것이 아니기 때문에 false가 반환된다.
        assert future.get() == 1; // 이미 위에서 1을 할당해서 complete 시켰기 때문에 future의 값은 여전히 1을 유지한다.
    }
}