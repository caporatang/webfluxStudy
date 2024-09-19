package com.example.webfluxstudy.reactor.context.contextRead;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.reactor.context.contextRead
 * fileName : ContextReadExample
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
@Slf4j
public class ContextReadExample {
    // Mono.deferContextual가 contextView를 인자로 전달하고 Mono를 반환값으로 받아서 Mono를 생성한다.
    // 이렇게 생성된 Mono를 flatMap으로 처리한다.

    public static void main(String[] args) {
        Flux.just(1)
                .flatMap(value -> {
                    return Mono.deferContextual(contextView -> {
                        // contextView 값을 일단 출력
                        String name = contextView.get("name");
                        log.info("name : " + name);
                        // 받은 value를 Mono.just 로 반환한다.-> Mono.just(value)로 호출한것과 같음
                        return Mono.just(value);
                    });
                }).contextWrite(context ->
                    context.put("name", "taeil")
                ).subscribe();

        // deferContextual 은 Mono.just(value)를 반환하고
        // 반환한 값은 flatMap을 통해서 다음 파이프라인에게 전달된다.
    }
}