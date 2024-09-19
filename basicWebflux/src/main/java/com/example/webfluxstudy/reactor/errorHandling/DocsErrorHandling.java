package com.example.webfluxstudy.reactor.errorHandling;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling
 * fileName : DocsErrorHandling
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
public class DocsErrorHandling {
    // 에러의 전파
    // Reactive streams에서 onError 이벤트가 발생하면 더 이상 onNext, onComplete 이벤트를 생산하지 않고 종료
    // Reactor에서 onError 이벤트가 발생하면 onError 이벤트를 아래로 전파한다.

    // onError 이벤트를 처리하기 위해서는?
    // 1. 고정된 값을 반환한다.
    // 2. publicsher를 반환한다.
    // 3. onComplete 이벤트로 변경한다.
    // 4. 다른 에러로 변환한다.
}