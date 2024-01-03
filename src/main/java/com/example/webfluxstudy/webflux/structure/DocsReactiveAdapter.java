package com.example.webfluxstudy.webflux.structure;

/**
 * packageName : com.example.webfluxstudy.webflux.structure
 * fileName : DocsReactiveAdapter
 * author : taeil
 * date : 1/3/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/3/24        taeil                   최초생성
 */
public class DocsReactiveAdapter {
    // Spring webflux는 어떻게 RxJava, Mutiny, Coroutine을 동시에 지원할 수 있는걸까 ?

    // ReactiveAdapterRegistry는 내부적으로 각각 라이브러리의 Publisher 매칭되는 변환함수를 Adapter 형태로 저장한다.
    // getAdapter를 통해서 변환하고자 하는 Publisher를 넘기고 해당 adapter의 toPublish를 이용하여, reactive streams의 Publisher로 변경한다.

    // 여러 라이브러리의 Publisher들을 reactive streams의 Publisher로 변환 가능하다.
    // Spring webflux에서는 reactive streams의 Publisher 기준으로 구현을 한다면 ReactiveAdapter를 통해서 여러 라이브러리 지원 가능

    // -> reactiveAdapter는 결국에 RxJava, Mutiny, Reactor, kotlin coroutine 등을 publisher를 바꿀 수 있는 방법을 저장해둔 어댑터..
}