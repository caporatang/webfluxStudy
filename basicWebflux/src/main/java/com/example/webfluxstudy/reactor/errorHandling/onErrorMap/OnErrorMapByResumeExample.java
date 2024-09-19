package com.example.webfluxstudy.reactor.errorHandling.onErrorMap;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling.onErrorMap
 * fileName : OnErrorMapByResumeExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class OnErrorMapByResumeExample {
    public static void main(String[] args) {
        // 에러를 다른 에러로 변환해서 출력하고 싶은 경우
        // onErrorResume과 Flux.error 혹은 Mono.error 를 사용하면 에러를 다른 에러로 변환하여 전달 가능하다.
        // 매번 이렇게 변경하고 커스텀하고 그러기엔 너무 복잡하고 코드가 길어진다.. -> onErrorMap을 사용하자

        Flux.error(new IOException(new IOException("fail to read file")))
                .onErrorResume(e ->
                        Flux.error(new CustomBusinessException("custom")))
                .subscribe(value -> {
                    log.info("value : " + value);
                }, e -> {
                    log.info("error " + e);
                });

    }

}