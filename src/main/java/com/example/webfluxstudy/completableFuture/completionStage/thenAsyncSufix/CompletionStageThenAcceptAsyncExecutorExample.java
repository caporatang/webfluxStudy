package com.example.webfluxstudy.completableFuture.completionStage.thenAsyncSufix;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage
 * fileName : CompletionStageThenAcceptAsyncExecutorExample
 * author : taeil
 * date : 2023/10/10
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/10        taeil                   최초생성
 */
@Slf4j
public class CompletionStageThenAcceptAsyncExecutorExample {
    // then*Async의 쓰레드풀 변경
    // thenAcceptAsync를 실행할때 특정한 파이프에 대해서만 다른 스레드에서 실행하고 싶은 경우

    // 모든 then*Async 연산자는 excutor를 추가 인자로 받는다.
    // 이를 통해서 다른 쓰레드풀로 task를 실행할 수 있다.

    public static void main(String[] args) throws InterruptedException {
        log.info("start main");
        var single = Executors.newSingleThreadExecutor();
        var fixed = Executors.newFixedThreadPool(10);

        CompletionStage<Integer> stage = Helper.finishedStage();
        stage.thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync", i);
        }, fixed).thenAcceptAsync(i -> { // fixed -> 고정
            log.info("{} in thenAcceptAsync2", i);
        }, single); // single로 설정
        log.info("after thenAccept");

        Thread.sleep(200);

        single.shutdown();
        fixed.shutdown();

        // 실행 순서
        // 1. start main
        // 2. Helper
        // 3. after thenAccept  -> 바로 종료 -> 이유는 동기적으로 동작하지 않기 때문이다. -> 동기적으로 동작하지 않고 비동기적이기 때문임

        // 2 는 ForkJoinPool 에서 동작
        // 1, 3 은 main 스레드에서 동작

        // 4. 1 in theAcceptAsync
        // 5. null in the thenAcceptAsync2

        // 4, 5 는 pool에 각각 존재하는 스레드에서 동작한다.

        // !! 인자로 ExcutorService 를 인자로 넘겨 줌으로서 각각의 다른 스레드에서 동작 시킬수있음.
    }
}