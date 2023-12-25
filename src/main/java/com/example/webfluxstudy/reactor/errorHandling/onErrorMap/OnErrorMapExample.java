package com.example.webfluxstudy.reactor.errorHandling.onErrorMap;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling.onErrorMap
 * fileName : OnErrorMapExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class OnErrorMapExample {
    // onError 이벤트를 처리하기 위해 다른 에러로 변환한다.
    // 다른 이벤트로 변환하여 저수준의 에러를 고수준의 에러, 비지니스 로직과 관련된 에러로 변환 가능하다.
    // 변환만 하기 때문에 추가적인 에러 핸들링은 여전히 필요하다.
    public static void main(String[] args) {
        log.info("start main");

        Flux.error(new IOException("fail to read file")) // IOException 발생!
                .onErrorMap(e -> new CustomBusinessException("custom")) // throwable을 받아서 custom으로 변경!
                .subscribe(value -> {
                    log.info("value : " + value);
                }, e -> {
                    log.info("error :" + e);
                });
        log.info("end main");
    }
}