package com.example.webfluxstudy.completableFuture.completionStage.exceptionally;

import com.example.webfluxstudy.completableFuture.completionStage.thenAsyncSufix.Helper;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage.exceptionally
 * fileName : CompletionStageExceptionallyExample
 * author : taeil
 * date : 2023/10/10
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/10        taeil                   최초생성
 */
@Slf4j
public class CompletionStageExceptionallyExample {
    public static void main(String[] args) throws InterruptedException {
        // Exception 핸들링에 유용하다!


        Helper.completionStage()
                .thenApplyAsync(i -> {
                    log.info("in thenApplyAsync");
                    return i / 0; // ArithmeticException 발생 !
                }).exceptionally(e -> {
                    // 발생한 익셉션을 잡아서 출력!
                    log.info("{} in exceptionally", e.getMessage());
                    // 값을 0으로 변환해서 다음 task로 반환!
                    return 0;
                }).thenAcceptAsync(value -> {
                    // 0을 출력
                    log.info("{} in thenAcceptAsync", value);
                });

        Thread.sleep(1000);
    }

}