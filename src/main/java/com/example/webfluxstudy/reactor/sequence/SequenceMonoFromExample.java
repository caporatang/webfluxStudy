package com.example.webfluxstudy.reactor.sequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence
 * fileName : SequenceMonoFromExample
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
@Slf4j
public class SequenceMonoFromExample {
    // mono from : 함수형 인터페이스를 실행하고 future를 실행하고 그 결과를 넘기는 방식

    public static void main(String[] args) {

        // fromCallable : Callable 함수형 인터페이스를 실행하고 반환값을 onNext로 전달
        Mono.fromCallable(() -> {
            return 1;
        }).subscribe(value -> {
            log.info("value fromCallable : " + value);
        });

        // fromFuture : Future를 받아서 done 상태가 되면 반환값을 onNext로 전달
        Mono.fromFuture(CompletableFuture.supplyAsync(() -> {
            return 1;
        })
        ).subscribe(value -> {
            log.info("value fromFuture : " + value);
        });

        // callable 과 supplier는 거의 유사하다.
        // 차이점은 Exception을 throw 하냐 안하냐의 차이다.

        // fromSupplier : Supplier 함수형 인터페이스를 실행하고 반환값을 onNext로 전달
        Mono.fromSupplier(() -> {
            return 1;
        }).subscribe(value -> {
            log.info("value fromSupplier : " + value);
        });

        // fromRunnable : Runnable 함수형 인터페이스를 실행하고 끝난후 onComplete 전달
        // Runnable 함수형 인터페이스의 특징은 어떤 인자도 받지 않고 어떤 결과도 받지 않음
        // Runnable은 값이 아니라 사건 자체가 전달해야하는 경우 -> 특정 작업을 수행하고 Mono로 넘겨야 하는 경우에 사용하멵 좋겠는데?
        Mono.fromRunnable(() -> {
            /* do nothing*/
        }).subscribe(null, null, () -> {
            log.info("complete fromRunnable");
        });
    }

}