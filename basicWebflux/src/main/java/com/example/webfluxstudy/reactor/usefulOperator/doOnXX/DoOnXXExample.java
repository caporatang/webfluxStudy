package com.example.webfluxstudy.reactor.usefulOperator.doOnXX;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.usefulOperator.doOnXX
 * fileName : DoOnXXExample
 * author : taeil
 * date : 12/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/28/23        taeil                   최초생성
 */
@Slf4j
public class DoOnXXExample {
    // doOnSubscribe, doOnNext, doOnComplete, doOnError 등
    // 각각의 이벤트를 흐름에 영향을 주지 않고
    // 위에서 내려오는 이벤트에 대해서 로깅이나 추가 작업이 가능하다

    // 위에서 전달 받은 이벤트에 대해서만 출력이 가능하기 때문에 doOnNext는 map을 지난 시점이다.

    public static void main(String[] args) {
        Flux.range(1, 5)
                .map(value -> value * 2)
                .doOnNext(value -> {
                    log.info("doOnNext : " + value);
                })
                .doOnComplete(() -> {
                    log.info("doOnComplete");
                })
                .doOnSubscribe(subscription -> {
                    log.info("doOnSubscription");
                })
                .doOnRequest(value -> {
                    log.info("doOnRequest : " + value);
                })
                .map(value -> value / 2 )
                // 별도의 consumer 없이 subscribe 하는 경우에 unbounded request가 발생 -> unbounded 는 Long Max만큼 발생한다.
                .subscribe();
    }
}