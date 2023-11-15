package com.example.webfluxstudy.basicReactiveProgramming.completableFuture;

/**
 * packageName : com.example.webfluxstudy.completableFuture
 * fileName : CompletableFuture
 * author : taeil
 * date : 2023/10/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/02        taeil                   최초생성
 */
public class CompletableFuture {
    // CompletableFuture는 Future와 CompletionStage 라는 부제를 가지고 있다!

    // 2014년에 발표된 java 8에서 처음 도입
    // 비동기 프로그래밍 지원
    // Lambda, Method reference 등 Java 8의 새로운 기능 지원

    // Future<T> , CompletionStage 를 구현한 클래스가 CompletableFuture
    // Future : 비동기적인 작업을 수행, 해당 작업이 완료되면 결과를 반환하는 인터페이스
    // CompletionStage : 비동기적인 작업을 수행, 해당 작업이 완료되면 결과를 처리하거나 다른 CompletionStage를 연결하는 인터페이스


    // Future와 CompletionStage의 특징과 한계점
    // Future는 비동기적인 action을 수행할 수는 있지만, get을 통해 접근해야 하기 때문에 비동기적으로 사용하긴 어려움이 있음 -> 연산자도 부족함
    // CompletionStage는 Future에서는 제공하지 않는 연산자들을 제공하고, 파이프라인처럼 처리할 수 있는 기능을 제공한다. (exceptionally)
    // Future, CompletionStage에는 별도로 Complete하는 메서드가 없음 -> 외부에서 CompletableFuture의 값을 변경할 수 없었음 -> complete 메서드 제공


    // Future 와 CompletableFuture의 상태 체크 (Future의 한계2 -> CompletableFuture의 편의성)
    // Future 에서의 상태 조회는 isDone, isCanceled
    // CompletableFuture 에서의 상태 조회는
    // 1. isDone
    // 2. completedExceptionally
    // 3. isCanceled
    // ex) isDone이 true 인지 체크한다 : Future가 종료 되었는지 체크, isCanceled 가 true라면 취소된 것, completedExceptionally가 true라면 진행 도중 예외가 생겼음을 알 수 있다.


    // CompletableFuture의 한계
    // 1. 지연 로딩 기능을 제공하지 않는다. : CompletableFuture를 반환하는 함수를 호출시 즉시 작업이 실행된다.
    //      -> (Thread sleep도 결국 별도의 쓰레드에서 실행되고 있는것이기 때문에 지연로딩이라 보기 어렵다.)

    // 2. 지속적으로 생성되는 데이터를 처리하기 어렵다. : CompletableFuture에서 데이터를 반환하고 나면 다시 다른 값을 전달하기 어렵다.
    //      -> (데이터를 하나씩 내려주기 힘들고, future에 값을 모아서 내려주거나, complete 시키는 식으로 처리 해야한다.)
}