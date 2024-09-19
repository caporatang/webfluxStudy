package com.example.webfluxstudy.reactor.errorHandling.onErrorComplete;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling.onErrorComplete
 * fileName : OnErrorCompleteExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class OnErrorCompleteExample {
    // onError 이벤트를 처리하기 위해 onComplete 이벤트로 변경
    // error 이벤트가 complete 이벤트로 변경되었기 때문에 errorConsumer가 동작하지 않는다.

    public static void main(String[] args) {
        Flux.create(sink -> {
                    sink.next(1);
                    sink.next(2);
                    sink.error(new RuntimeException("error"));
                })
                .onErrorComplete() // error 발생 시 error를 complete 이벤트로 변경해서 다음 subscribe에게 전달한다.
                .subscribe(value -> {
                    log.info("value : " + value);
                }, e -> {
                    log.info("error : " + e);
                }, () -> {
                    log.info("complete");
                });
    }

}