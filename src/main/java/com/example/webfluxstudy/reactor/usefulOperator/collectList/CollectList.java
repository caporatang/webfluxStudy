package com.example.webfluxstudy.reactor.usefulOperator.collectList;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.usefulOperator.collectList
 * fileName : DocsCollectList
 * author : taeil
 * date : 12/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/28/23        taeil                   최초생성
 */
@Slf4j
public class CollectList {
    // next 이벤트가 전달되면 내부에 item을 저장한다.
    // complete 이벤트가 전달되면 저장했던 item 들을 list 형태로 만들고 아래에 onNext를 발행한다.
    // 즉시 complete 이벤트가 발행된다.
    // Flux를 Mono로 바꿀때 유용하다

    public static void main(String[] args) {
        // 일반적으로 range로 출력하면 1 ~ 5 가 하나씩 출력되지만 collectList를 사용하면 하나의 배열로 값이 반환된다. -> [1, 2, 3, 4, 5]
        // 어떤 상황에서 쓰면 좋을까? -> n 명의 유저 중 나이가 제일 적은 유저를 골라야할 때 값을 하나씩 받기 보다는 collectList로 반환 받은 후 처리하자. -> 당연한 얘기지만?
        Flux.range(1, 5)
                .collectList()
                .doOnNext(value -> {
                    log.info("doOnNext : " + value);
                })
                .subscribe();
    }

}