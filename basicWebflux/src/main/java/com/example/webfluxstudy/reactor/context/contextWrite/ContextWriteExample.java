package com.example.webfluxstudy.reactor.context.contextWrite;

import com.example.webfluxstudy.reactor.context.ContextLogger;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.context.contextWrite
 * fileName : ContextWriteExample
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
@Slf4j
public class ContextWriteExample {
    // subscribe부터 시작하여 점차 위로 올라가며 contextWrite를 만나면 실행하고 새로운 context를 생성해서 위에 있는 연산자에 전달한다.
    // context write는 subscribe부터 위로 전파한다.
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        // 1 이라는 이름으로 값을 찍고
        // 2 이라는 이름으로 wooman을 찍고
        // 3 이라는 이름으로 taeil을 찍겠다.
        // 그대로 실행이 될까?

        // name: 1, context: Context1{name=wooman}
        // name: 2, context: Context1{name=taeil}
        // name: 3, context: Context0{}
        // subscribe부터 위로 전파 되기 때문에 처음에는 값이 없는 상태의 flatMap이 실행되기 때문에 예상과는 다르게 실행된다......
        // -> 초기화 하려면, 즉 초기값을 생성하려몀ㄴ contextInitial 을 사용하면 된다
        Flux.just(1)
                .flatMap(v -> ContextLogger.logContext(v, "1"))
                .contextWrite(context ->
                        context.put("name", "wooman"))
                .flatMap(v -> ContextLogger.logContext(v, "2"))
                .contextWrite(context ->
                        context.put("name", "taeil"))
                .flatMap(v -> ContextLogger.logContext(v, "3"))
                .subscribe();
        log.info("end main");
    }
}