package com.example.webfluxstudy.reactor.connection.concat;

/**
 * packageName : com.example.webfluxstudy.reactor.connection.concat
 * fileName : DocsConcat
 * author : taeil
 * date : 12/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/27/23        taeil                   최초생성
 */
public class DocsConcat {
    // Publisher를 다른 Publisher와 합치는 연산자
    // 앞의 Publisher가 onComplete 이벤트를 전달하면 다음 Publisher를 subscribe
    // 각각 publisher의 onNext 이벤트가 전파된다.
    // 따라서 Publisher의 순서가 보장된다.
}