package com.example.webfluxstudy.basicReactiveProgramming.functionalInterface.blockingNonBlocking;

/**
 * packageName : com.example.webfluxstudy.blockingNonBlocking
 * fileName : IoNioBlockNonBlock
 * author : taeil
 * date : 2023/10/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/02        taeil                   최초생성
 */
public class IoNioBlockNonBlock {
    // 동기 Blocking : A.class
    // 1. caller가 callee를 호출
    // 2. callee에서 응답(getResult)을 만드는 동안 caller는 아무것도 하지 못하고 대기 상태 (Blocking)

    // 비동기 Blocking ; B.class
    // 1. caller가 callee를 호출
    // 2. callee에서 응답(getResult)을 만드는 동안 대기 상태인것은 동일 하지만, callback까지 실행한 후 응답을 준다.

    // 동기 Non-blocking : C.class
    // 1. caller가 callee를 호출
    // 2. future 객체를 이용해서 작업 중간 중간 얼마나 진행되었는지 확인 -> 동기이기 때문에 결과값이 있어야 작업이 가능하기 때문이다.
    // 3. isDone 의 결과에 따라서 다음 작업으로 넘어감

    // 비동기 Non-blocking : D.class
    // 1. caller가 callee를 호출
    // 2. 호출한 시점에 바로 응답이 돌아오고 caller는 caller의 작업을 하고, callee는 callee의 작업을 한다.



    //                      동기                              비동기
    // Blocking         caller는 아무것도 할 수 없는 상태      caller는 아무 것도 할 수 없는 상태.
    //                  결과를 얻은 후 직접 처리              결과는 callee가 처리한다.
    // Non-blocking     caller는 자기 할 일을 할 수 있다.     caller는 자기 할 일을 할 수 있다.
    //                  결과를 얻은 후 직접 처리              결과는 callee가 처리한다.
}