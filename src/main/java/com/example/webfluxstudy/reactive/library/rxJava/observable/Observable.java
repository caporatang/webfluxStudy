package com.example.webfluxstudy.reactive.library.rxJava.observable;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.observable
 * fileName : Observable
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
public class Observable {
    // 0..n개의 item을 전달
    // 에러가 발생하면 error signal 전달하고 종료
    // 모든 item을 전달했다면 complete signal 전달하고 종료
    // backPressure 지원되지 않음

    // 요청이 들어오면 그걸 적재해서 갖고 있다가 요청하는 방식이 아니라
    // 준비가 되면 push 하는 방식이기 떄문에 backPressure는 지원하지 않는다.

}