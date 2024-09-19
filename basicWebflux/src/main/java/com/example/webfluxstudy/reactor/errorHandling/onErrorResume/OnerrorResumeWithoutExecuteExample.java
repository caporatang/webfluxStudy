package com.example.webfluxstudy.reactor.errorHandling.onErrorResume;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling.onErrorResume
 * fileName : OnerrorResumeWithoutExecuteExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class OnerrorResumeWithoutExecuteExample {
    // Mono.just 혹은 Flux.just와 함께 사용한다면 ?
    // error 이벤트가 발생한 상황에만 apply를 실행하기 때문에 불필요하게 shouldDoOnError를 호출하지 않는다.
    // publisher를 받기 때문에 Flux 뿐만 아니라 Mono도 사용 가능하다
    public static void main(String[] args) {
        Flux.just(1)
                .onErrorResume(e ->
                        Mono.just(shouldDoOnError())
                ).subscribe(value -> {
                    log.info("value : " + value);
                });
    }

    private static int shouldDoOnError() {
        log.info("shouldDoOnError");
        return 0;
    }

}