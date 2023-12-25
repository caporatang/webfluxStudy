package com.example.webfluxstudy.reactor.errorHandling;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling
 * fileName : DoOnErrorExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class DoOnErrorExample {
    // 파이프라인 흐름에 영향을 주지 않고 error logging만 가능하다
    public static void main(String[] args) {
        Flux.error(new RuntimeException("error"))
                .doOnError(error -> {
                    log.info("doOnError: " +error );
                }).subscribe(value -> {
                    log.info("value : " + value);
                }, error -> {
                    log.info("error : " + error);
                });
    }
}