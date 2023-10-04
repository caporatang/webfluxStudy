package com.example.webfluxstudy.completableFuture.completionStage;

/**
 * packageName : com.example.webfluxstudy.completableFuture
 * fileName : completionStage
 * author : taeil
 * date : 2023/10/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/04        taeil                   최초생성
 */
public class CompletionStage {
    // CompletionStage 에서 제공되는 메서드들은 파이프 라인이라고 생각하면 편하다.
    // 50개에 가까운 연산자들을 활용하여 비동기 task들을 실행하고 값을 변형하는 등 chaining을 이용한 조합 가능
    // 에러를 처리하기 위한 콜백이 따로 제공된다.


    // ForkJoinPool - thread pool -> ExcutorService에서 한번 다뤘었음..
    // CompletableFuture는 내부적으로 비동기 함수들을 실행하기 위해 ForkJoinPool을 사용한다.
    // ForkJoinPool의 기본 size = 할당된 cpu 코어 - 1
    // 데몬 쓰레드. main 쓰레드가 종료되면 즉각적으로 종료된다

    // ForkJoinPool - fork & join
    // Task를 fork를 통해서 subtask로 나누고, Thread pool에서 steal work 알고리즘을 이용해서 균등하게 처리, -> join을 통해서 결과를 생성한다.
    // CompletionStage, CompletableFuture는 내부적으로 ForkJoinPool을 사용한다.
}