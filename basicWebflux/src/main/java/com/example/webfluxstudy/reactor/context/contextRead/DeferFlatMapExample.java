package com.example.webfluxstudy.reactor.context.contextRead;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.reactor.context.contextRead
 * fileName : DeferFlatMapExample
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
@Slf4j
public class DeferFlatMapExample {
    // defer와 flatMap을 같이 사용한다면 ?

    // Mono.defer는 주어진 supplier를 실행해서 Mono를 구하고 해당 Mono로 이벤트를 전달한다.
    // v라는 값이 있을때 Mono.defer(() -> Mono.just(v))를 한다면 ? -> 이는 결국 Mono.just(v)와 동일하다.
    // flatMap(v -> Mono.defer(() -> Mono.just(v))) = flatMap(v -> Mono.just(v))) = map(v -> v)

    // -> 어려운ㄷ ㅔ ..?
    public static void main(String[] args) {
        Mono.just(1)
                .flatMap(v -> Mono.defer(()-> {
                    return Mono.just(v);
                })).subscribe(n -> {
                    log.info("next : {}", n);
                });
    }
}