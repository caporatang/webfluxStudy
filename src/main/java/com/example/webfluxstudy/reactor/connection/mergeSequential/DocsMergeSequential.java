package com.example.webfluxstudy.reactor.connection.mergeSequential;

/**
 * packageName : com.example.webfluxstudy.reactor.connection.mergeSequential
 * fileName : DocsMergeSequential
 * author : taeil
 * date : 12/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/27/23        taeil                   최초생성
 */
public class DocsMergeSequential {
    // Publisher를 다른 Publisher와 합치는 연산자.
    // 모든 Publisher를 바로 subscribe
    // 각각 Publisher의 onNext 이벤트가 동시에 도달한다.
    // merge와 다르게 내부에서 재정렬하여 순서를 보장한다

    // -> concat과 merge의 장점을 합친게 MergeSequential 이다.
}