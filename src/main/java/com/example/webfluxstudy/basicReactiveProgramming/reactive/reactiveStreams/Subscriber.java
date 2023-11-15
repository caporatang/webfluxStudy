package com.example.webfluxstudy.basicReactiveProgramming.reactive.reactiveStreams;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams
 * fileName : Subscriber
 * author : taeil
 * date : 2023/10/17
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/17        taeil                   최초생성
 */
public class Subscriber {
    // Subscriber는 각각의 이벤트가 들어오는 채널을 구현한다.

    // subscribe 하는 시점에 publisher로 부터 subscription을 받을 수 있는 인자 제공
    // onNext, onError, onComplete를 통해서 값이나 이벤트를 받을 수 있다.
    // onNext는 여러 번, onError나 onComplete는 딱 한 번만 호출된다.
}