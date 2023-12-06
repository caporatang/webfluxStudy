package com.example.webfluxstudy.reactor.sequence.generate;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence.generate
 * fileName : SequenceGenerateExample
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
@Slf4j
public class SequenceGenerateExample {
    // generate
    // 초기값을 0으로 세팅
    // generator에서 현재 state를 next로 전달
    // 만약 state가 9라면 complete 이벤트를 전달
    // state + 1 을 전달

    public static void main(String[] args) {
        Flux.generate(
                () -> 0,            // 초기값  세팅
                (state, sink) -> {  // 최초 실행이라면 0이 들어있을거고.. sink는 이벤트를 전달할수있겠지?
                    sink.next(state); // next로 0 을 흘려보내주겠지.
                    if (state == 9) { // 9와 같지 않으니까 complete는 호출되지 않겠지.

                        // 9가 되면 complete가 호출되면 루프를 타지 않고 종료가 되겠지!
                        sink.complete();
                    }
                    // 그럼 초기값 0 + 1 이 리턴이 되겠지 -> 다음 generate에서는 초기값이 1이 세팅이 되겠지
                    return state + 1;
                }
        ).subscribe(value -> {
            log.info("value : " + value);
        }, error -> {
            log.error("error : " + error);
        }, () -> {
            log.info("complete");
        });
    }
}