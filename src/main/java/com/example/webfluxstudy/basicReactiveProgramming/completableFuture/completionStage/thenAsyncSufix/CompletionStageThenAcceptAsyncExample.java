package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completionStage.thenAsyncSufix;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage
 * fileName : CompletionStageThenAcceptAsyncExample
 * author : taeil
 * date : 2023/10/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/04        taeil                   최초생성
 */
@Slf4j
public class CompletionStageThenAcceptAsyncExample {

    // thenAcceptAsync는 main 스레드가 아닌 ForkJoinPool에서 실행된다..!
    // 반명 thenAccept는 future를 리턴받고 순차적으로(약간 동기화) 처리가 되는 것을 알 수 있다..

    // thenAccept[Async]의 실행 쓰레드
    // done 상태에서 thenAccept는 caller(main)의 쓰레드에서 실행
    // done 상태의 compeltionStage에 thenAccept를 사용하는 경우, caller 쓰레드를 block할 수 있다.

    @SneakyThrows
    public static void main(String[] args) {
      log.info("start main");

        CompletionStage<Integer> stage = Helper.finishedStage();

        stage.thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync", i);
        }).thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync2", i);
        });

        log.info("after thenAccept");
        Thread.sleep(100);
    }
}