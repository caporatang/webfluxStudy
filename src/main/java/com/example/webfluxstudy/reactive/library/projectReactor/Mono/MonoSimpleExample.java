package com.example.webfluxstudy.reactive.library.projectReactor.Mono;

import com.example.webfluxstudy.reactive.library.projectReactor.SimpleSubscriber;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Mono
 * fileName : MonoSimpleExample
 * author : taeil
 * date : 2023/10/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/20        taeil                   최초생성
 */
@Slf4j
public class MonoSimpleExample {
    // Flux를 사용해서 하나의 값만 넘겨주면 되는데 왜 Mono라는 별도의 publisher를 사용해야 할까 ?
    // 1. 1개의 item만 전달하기 때문에 next 하나만 실행하면 complete가 보장된다.
    // 2. 혹은 전달하지 않고 complete를 하면 값이 없다는 것을 의미한다.
    // 3. 하나의 값이 있거나 없다.

    // Mono<T> : Optional<t>
    // 없거나 혹은 하나의 값을 나타낸다.
    // Mono<Void>로 특정 사건이 완료되는 시점을 가리킬 수도 있다. -> 끝나면 자동으로 complete 되기 떄문이다.
    // 있거나 없거나 둘 중 하나이기 떄문에 명확하게 사용할 수 있다

    // Flux<T> : List<T>
    // Flux는 무한하거나 유한한 여러개의 값을 나타낸다.

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        getItems().subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");

        Thread.sleep(1000);
    }

    private static Mono<Integer> getItems() {
        return Mono.create(monoSink -> {
            // success 를 호출해서 하나의 값만 전달하게 되면 별도의 complete를 작성하지 않아도 자동으로 complete 된다.
            monoSink.success(1);
        });
    }
}