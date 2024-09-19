package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completableFuture.complete;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.complete
 * fileName : Complete
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
public class Complete {
    // Future, CompletionStage에는 별도로 Complete하는 메서드가 없음 -> 외부에서 CompletableFuture의 값을 변경할 수 없었음

    // CompletableFuture가 완료되지 않았다면 주어진 값으로 채운다.
    // complete에 의해서 상태가 바뀌었다면 true, 아니라면 false를 반환한다.
}