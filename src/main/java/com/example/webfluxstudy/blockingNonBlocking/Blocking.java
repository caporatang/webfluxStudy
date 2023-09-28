package com.example.webfluxstudy.blockingNonBlocking;

/**
 * packageName : com.example.webfluxstudy.blockingNonBlocking
 * fileName : Blocking
 * author : taeil
 * date : 2023/09/28
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/28        taeil                   최초생성
 */
public class Blocking {
    // Blocking
    // callee를 호출한 후, callee가 완료되기 전까지 caller가 아무것도 할 수 없다.
    // 제어권을 callee가 가지고 있다.

    // A모델
    // main은 getResult가 결과를 돌려주기 전까지 아무것도 할 수 없다.
    // main은 getResult가 완료될 때까지 대기한다.

    // B 모델
    // main은 getResult가 결과를 구하고 callback을 실행하기 전까지 아무것도 할 수 없다.
    // main은 getResult가 완료될 때까지 대기한다.

    // caller는 callee가 완료될때까지 대기한다.
    // 제어권을 callee가 가지고 있다.
    // caller와 다른 별도의 thread가 필요하지 않다 -> thread를 추가로 쓸 수도있다.

}