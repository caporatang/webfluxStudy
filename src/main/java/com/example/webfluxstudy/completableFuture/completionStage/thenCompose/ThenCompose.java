package com.example.webfluxstudy.completableFuture.completionStage.thenCompose;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completionStage.thenCompose
 * fileName : ThenCompose
 * author : taeil
 * date : 2023/10/10
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/10        taeil                   최초생성
 */
public class ThenCompose {
    // thenCompose[Async] : 다른 Future 를 조합해야 할 떄 유용하다.

    // Function을 파라미터로 받는다.
    // 이전 task로부터 T 타입의 값을 받아서 가공하고 U 타입의 CompletionStage를 반환한다.
    // 반환한 CompletionStage가 done 상태가 되면 값을 다음 task에 전달한다.
    // 다른 future를 반환해야하는 경우 유용하다.

}