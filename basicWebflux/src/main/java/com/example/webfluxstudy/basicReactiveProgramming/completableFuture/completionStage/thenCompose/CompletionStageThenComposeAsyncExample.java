package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completionStage.thenCompose;

import com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completionStage.thenAsyncSufix.Helper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage.thenCompose
 * fileName : CompletionStageThenComposeAsyncExample
 * author : taeil
 * date : 2023/10/10
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/10        taeil                   최초생성
 */
@Slf4j
public class CompletionStageThenComposeAsyncExample {
    // thenComposeAsync
    // Future를 가지고 연계를 해야 한다면 thenComposeAsync를 사용하자

    public static void main(String[] args) throws InterruptedException {
        log.info("start main !");

        CompletionStage<Integer> stage = Helper.completionStage();

        // thenApply 였으면 값을 그냥 전달하기 때문에 Future 형태가 아니라 CompletionStage 였을것..!
        stage.thenComposeAsync(value -> {
            var next = Helper.addOne(value); // 값을 받아서 값에 +1한 결과를 100미리 세컨드 후에 Future 형태로 반환
            log.info("in thenComposeAsync : {}", next); // NotCompleted 상태의 Future 반환 -> next에는 Future가 할당되어 있음.
            return next;
        }).thenComposeAsync(value -> {
            var next = Helper.addResultPrefix(value);
            log.info("in thenComposeAsync2 : {}", next); // NotCompleted 상태의 Future 반환 : addResultPrefix는 단순히 문자열 result: 를 붙여주는 함수임.
            return next;
        }).thenAcceptAsync(value -> {
            log.info("{} in thenAcceptAsync", value); // 결과로 result 2 출력
        });

        Thread.sleep(1000);

    }
}