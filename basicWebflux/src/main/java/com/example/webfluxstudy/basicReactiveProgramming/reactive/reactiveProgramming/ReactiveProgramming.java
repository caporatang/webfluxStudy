package com.example.webfluxstudy.basicReactiveProgramming.reactive.reactiveProgramming;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveProgramming
 * fileName : ReactiveProgramming
 * author : taeil
 * date : 2023/10/17
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/17        taeil                   최초생성
 */
public class ReactiveProgramming {
    // Reactive stream
    // callee는 caller에게 응답이 아닌 publisher를 제공한다.
    // callee는 각가의 값을 publisher 를 통해서 전달한다.
    // caller는 해당 publisher를 subscribe 하거나 다른 caller에게 전달한다.
    // caller는 subscriber를 등록하여 back-pressure를 조절하여 처리 가능한만큼 전달 받는다.

    // Reactive manifesto 와 Reactive stream
    // manifesto : 구성 요소는 서로 비동기적으로 메시지를 주고 받으며, 독립적인 실행을 보장한다.
    // -> callee는 publisher를 반환하고 caller는 subscriber를 등록한다. 이 과정에서 caller와 callee는 비동기적으로 동작한다.
    // manifesto : 메시지 큐를 생성하고 배압을 적용하여 부하를 관리하고 흐름을 제어한다.
    // -> publisher는 메시지 큐를 생성해서 부하를 관리하고 흐름을 제어한다. back-pressure를 조절할 수 있는 수단을 제공한다.
    // caller는 publisher에게 처리 가능한 양을 알려주고, publisher는 실제로 처리 가능한 양 만큼만 데이터를 요청한다.

    // Reactive programming
    // 비동기 데이터 stream을 사용하는 패러다임.
    // 모든 것이 이벤트로 구성되고 이벤트를 통해서 전파 : event-driven 해야 한다. 데이터의 전달, 에러, 완료 까지 모두 이벤트로 취급해야 한다.
    //  -> 이 조건들을 가장 쉽게 만족하고 지원하는것이 ReactiveStreams(라이브러리)
    // Reactive manifesto의 Responsive, Resilient, Elastic, Message Driven 까지 모두 해당된다.


    // Reactive system 에서는 message-driven을 강조, Reactive programming에서는 왜 event-driven을 강조하는 것일까?
    // message : event, command, query 등 다양한 형태를 수용한다.
    // message-drive은 event, command, query 등 다양한 형태의 메시지를 비동기적으로, 가능하다면 배압을 적용해서, 전달하는 형태에 집중한다.
    // event-driven은 message의 형태를 event로 제한.

    // !! event-driven은 message-driven의 일종이다. -> completion, error , 값 까지도 이벤트의 형태로 전달한다. -> reactive programming은 reactive manifesto의 원칙을 지킨다.
}