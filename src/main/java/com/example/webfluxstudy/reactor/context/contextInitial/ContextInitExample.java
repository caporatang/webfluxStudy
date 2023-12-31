package com.example.webfluxstudy.reactor.context.contextInitial;

import com.example.webfluxstudy.reactor.context.ContextLogger;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

/**
 * packageName : com.example.webfluxstudy.reactor.context.contextInitial
 * fileName : ContextInitExample
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
@Slf4j
public class ContextInitExample {
    // subscribe에 4번째 인자로 초기 값 전달이 가능하다.
    // 이 경우에도 context write과 동이랗게 subscribe 부터 위로 전파된다.

    public static void main(String[] args) {
        // key value 저장
        var initialContext = Context.of("name", "taeil");

        Flux.just(1)
                .flatMap(v -> ContextLogger.logContext(v, "1"))
                .contextWrite(context ->
                    context.put("name", "happy new year ~ "))
                .flatMap(v -> ContextLogger.logContext(v, "2"))
                .subscribe(null, null, null, initialContext); // 초기 컨텍스트의 값
    }
}