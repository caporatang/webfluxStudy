package com.example.webfluxstudy.reactor.sequence.generate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence.generate
 * fileName : DocsGenerate
 * author : taeil
 * date : 12/6/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/6/23        taeil                   최초생성
 */
public class DocsGenerate {
    // 리액터에서는 데이터의 유연한 컨트롤, 복잡한 연산을 위해 Generate 연산자를 제공한다.
    // public static <T,S> Flux<T> generate(
    //        Callable<S> stateSupplier,
    //        BiFunction<S, SynchronousSink<T>, S> generator
    // )
    // 동기적으로 Flux를 생성한다. -> generate를 하나씩 순차적으로 onNext로 전달하는 형태를 갖는다는 뜻이다.

    // stateSupplier : 초기값을 제공하는 Callable -> 최초값을 전달할때 사용한다.

    // generator
    // 1. 첫 번째 인자로 state를 제공한다. 변경된 state를 반환한다. 이 state로 종료 조건을 지정
    // 2. 두 번째 인자로 SynchronousSink를 제공한다. 명시적으로 next, error, complete 호출 가능
    // 3. 한 번의 generator 에서 최대 한 번만 next 호출이 가능함

    // BiFunction 함수형 인터페이스
    // 두개의 인자를 받아서 하나의 결과를 만들어내는 함수형 인터페이스임
}