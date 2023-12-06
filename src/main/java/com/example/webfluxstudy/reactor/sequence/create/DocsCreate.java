package com.example.webfluxstudy.reactor.sequence.create;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence.create
 * fileName : DocsCreate
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
public class DocsCreate {
    // 한번의 루트에서, 혹은 next를 여러번 호출하거나 복잡한 케이스를 커버해야하는 경우에 사용하ㅈ

    // 비동기적으로 Flux를 생성

    // FluxSink를 노출
    // 명시적으로 next, error, complete 호출 가능
    // SynchronousSink 와 다르게 여러 번 next 가능
    // 여러 thread에서 동시에 호출 가능

}