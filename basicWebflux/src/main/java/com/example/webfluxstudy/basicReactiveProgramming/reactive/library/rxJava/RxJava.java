package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.rxJava;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava
 * fileName : RxJava
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
public class RxJava {
    // Netflix 사에서 개발
    // 닷넷 프레임워크를 지원하는 Reactive Extensions를 포팅
    // Flowable, Observable, Single, Maybe, Completable, publisher 제공

    // 넷플릭스 자체적인 구현에 가까움,
    // 자바 6를 지원하기 떄문에 문법적으로 불편함이 있음.

    // Observable
    // Push 기반 : 준비되면 subscriber의 상태와 상관없이 무조건 전달
    // Subscriber가 처리할 수 없더라도 아이템을 전달한다
    // Reactive manifesto의 message driven을 일부만 준수한다.
    // onSubscribe로 Disposable을 전달한다. -> Subscription에는 reuqest, cancel 두가지가 지원되는데, Disposable은 cancel 메서드만 지원한다

    // Flowable
    // Pull 기반
    // Subscriber가 request의 수를 조절한다 : backPressure
    // Reactive manifesto의 message driven을 모두 준수
    // onSubscribe시 Subscription 전달


    //              onSubscribe                  onNext      onComplete             onError
    // Flowable   backPressure, cancelation       0..n     언제든지 가능             언제든지 가능
    // Observable   cancelation                   0..n     언제든지 가능             언제든지 가능
    // Single       cancelation                     1      onNext이후 바로          언제든지 가능
    // Maybe        cancelation                   0..1     언제든, onNext이후 바로    언제든지 가능
    // Completable  cancelation                    0       언제든지 가능              언제든지 가능

}