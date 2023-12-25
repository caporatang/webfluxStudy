package com.example.webfluxstudy.reactor.errorHandling.onErrorResume;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling.onErrorResume
 * fileName : OnErrorResumeExample
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
@Slf4j
public class OnErrorResumeExample {
    // OnErrorReturn이 에러가 발생하지 않아도 함수가 무조건 실행되는 문제를 해결할 수 있는 방법임.

    // onError 이벤트를 처리하기 위해 publisher를 반환하는 추상 함수를 실행한다.
    // 해당 publisher의 onNext, onError, onComplete 이벤트를 아래로 전달한다.

    // error 이벤트가 발생한 상황에서만 apply를 실행한다.
    public static void main(String[] args) {
        Flux.error(new RuntimeException("error")) // 에러 발생
                .onErrorResume(new Function<Throwable, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(Throwable throwable) {
                        // 에러가 발생 했을 때 Flux객체 0 -1 -2를 리턴 -> 다음 subscribe에게 전달
                        return Flux.just(0, -1, -2);
                    }
                })
                .subscribe(value -> {
                    log.info("value : " + value);
                });
    }
}