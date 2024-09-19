package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completableFuture.allOf;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.allOf
 * fileName : AllOf
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
public class AllOf {
    // CompletableFuture는 각각 다 다른 쓰레드에서 동작한다.
    // CompletableFuture를 여러개 만들어서 동시에 실행한 다음 그 결과를 한 지점에서 모아서 처리하고 싶을 때 사용한다.
    // ex) A, B, C Future를 동시에 실행하고 A, B, C 에 대한 결과를 다른 쓰레드에 내려 줘야 하는 상황

    // 여러 completableFuture를 모아서 하나의 completableFuture로 변환할 수 있다.
    // -> allOf의 반환값은 Void , 완료된 값을 반환하는게 아니라 여러개의 CompletableFuture가 전부 Done이 된 시점을 알 수 있다. -> 종료된 시점에 get으로 접근한다.

    // 모든 completableFuture가 완료되면 상태가 done으로 변경
    // Void 반환이므로 각각의 값에 get으로 접근해야 한다.
}