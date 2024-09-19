package com.example.webfluxstudy.reactor.usefulOperator.cache;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.usefulOperator.cache
 * fileName : CacheExample
 * author : taeil
 * date : 12/28/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/28/23        taeil                   최초생성
 */
@Slf4j
public class CacheExample {
    // 처음 subscribe에만 publisher 를 실행한다.
    // 그 이후 subscribe 부터는 저장한 event를 순서대로 흘려준다

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(sink ->  {
            for (int i = 0; i < 3; i++) {
                log.info("next : " + i);
                sink.next(i); // flux 시퀀스 생성
            }
            log.info("complete in publisher");
            sink.complete();
        }).cache(); // 캐시가 없다면 subscribe 할때마다 내부 내용이 실행되겠지 ?

        flux.subscribe(value -> {
            // 첫번째는 publisher 실행된다.
            log.info("value : " + value);
        }, null, () -> {
            log.info("complete");
        });

        flux.subscribe(value -> {
            // 캐싱처리 되어 있는 내용을 기반으로 이벤트를 전달받아서 처리할 수 있다.
            // -> value만 찍히고 next에 대한 로그는 찍히지 않는다
            // complete in publisher도 출력되지 않는다
            // -> DB에서 유저 정보를 가지고 와서 몇번의 재사용이 필요하다고 했을 때 DB를 여러번 조회하지 않고 다음 subscribe에게 넘겨줄수도있다 -> DB 쿼리 재실행할 필요가 없어진다.

            log.info("value: " + value);

        }, null, () -> {
            log.info("complete");
        });
    }
}