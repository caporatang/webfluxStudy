package com.example.webfluxstudy.reactor.context.contextRead;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.reactor.context.contextRead
 * fileName : Docs
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
@Slf4j
public class DeferExample {
    // 연산자
    // publisher를 생성하는 Consumer를 인자로 받아서 publisher를 생성하고,
    // 생성된 publisher의 이벤트를 아래로 전달한다.
    public static void main(String[] args) {
        Mono.defer(() -> {
            return Mono.just(1); // 컨슈머를 실행한 후에 그 반환값을 아래로 내려보내는 연산자임
        }).subscribe(n -> {
            log.info("next : {}", n);
        });
    }

}