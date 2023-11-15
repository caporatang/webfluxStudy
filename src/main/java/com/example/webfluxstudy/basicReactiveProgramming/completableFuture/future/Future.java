package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.future;

/**
 * packageName : com.example.webfluxstudy.completableFuture.future
 * fileName : Future
 * author : taeil
 * date : 2023/10/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/02        taeil                   최초생성
 */
public class Future {
    // Future 인터페이스의 한계
    // 1. cancel을 제외하고 외부에서 future를 컨트롤 할 수  없다.
    // 2. 반환된 결과를 get() 해서 접근하기 때문에 비동기 처리가 어렵다.
    // 3. 완료되거나 에러가 발생했는지 구분하기가 어렵다 ..! -> 상태를 isDone으로 체크를 해야 하는데, isDone은 동작만 안한다면 무조건 true 반환이기 때문에 구분하기 어렵다.
}