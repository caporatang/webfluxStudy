package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completionStage.thenApply;

import com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completionStage.thenAsyncSufix.Helper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage.thenApply
 * fileName : CompletionStageThenApplyAsyncExample
 * author : taeil
 * date : 2023/10/10
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/10        taeil                   최초생성
 */
@Slf4j
public class CompletionStageThenApplyAsyncExample {
    // 값을 변형해서 전달해야 하는 경우 유용!

    public static void main(String[] args) throws InterruptedException {
        CompletionStage<Integer> stage = Helper.completionStage(); // Integer 1 반환
        stage.thenApplyAsync(value -> {
            var next = value + 1;
            log.info("in thenApplyAsync: {}", next);
            return next;
        }).thenApplyAsync(value -> {
            var next =  "result: " + value;     // Integer를 String 으로 변환
            log.info("in thenApplyAsync: {}", next);
            return next;
        }).thenApplyAsync(value -> {
            var next = value.equals("result: 2"); // 변환된 문자열이 동일한지 체크 -> boolean 으로 변환!
            log.info("in thenApplyAsync: {}", next);
            return next;
        }).thenAcceptAsync(value -> log.info("{}", value)); // thenAcceptAsync는 반환값 없이 주어진 값으로 동작을 수행하기 떄문에 마지막에 로그 출력..!

        Thread.sleep(100);
    }



}