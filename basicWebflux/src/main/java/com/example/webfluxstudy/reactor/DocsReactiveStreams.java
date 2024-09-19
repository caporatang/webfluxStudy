package com.example.webfluxstudy.reactor;

/**
 * packageName : com.example.webfluxstudy.reactor
 * fileName : DocsReactiveStreams
 * author : taeil
 * date : 11/27/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/27/23        taeil                   최초생성
 */
public class DocsReactiveStreams {
    // Reactor와 연산자

    // Reactive Streams
    // 비동기 데이터 스트름 처리를 위한 표준
    // Publisher는 Subscriber에게 비동기적으로 이벤트를 전달

    // subscription을 통해서 back-pressure 기능을 사용
    // onSubscribe : subscriber가 publisher 사이에 연결이 시작될떄 호출. Subscription 객체를 전달
    // onNext : Publisher가 데이터를 생성하고 Subscriber에게 전달. Subscriber는 데이터를 받고 처리한다.
    // onComplete : 모든 아이템이 전달 완료. 더 이상 전달할 데이터가 없을때 호출. Publisher 와 Subscriber 의 연결이 종료된다
    // onError : 데이터 스트림 처리 중 오류가 발생 했을때 호출. 오류 정보를 전달하고 Publisher와 Subscriber의 연결이 종료

    // Subscription
    // request : Subscriber가 Publisher에게 n개의 데이터를 요청. Subscriber가 처리 가능한 만큼만 요청한다.
    // cancel : Subscriber가 데이터 요청을 취소하고 연결을 종료한다. Subscriber가 더 이상 데이터를 받지 않거나 에러가 발생한 경우 호출한다.

    // Reactor
    // Reactive streams 를 구현한 비동기 데이터 스트림 처리를 지원
    // ㄹSpring webflux에서 메인으로 사용되고 있다.
    // back-pressure를 제공하여 안정성을 높임.
    // 다양한 연산자로 다양한 연산들을 조합하여 가독성 증대


    // Reactor와 컨베이어 벨트
    // 컨베이어 벨트에 비유
    // 데이터는 소스(Publisher)에서 나와서 소비자(Subscriber) 에게 전달
    // 소스로부터 나온 '원재료' 는 다양한 변형과 중간 단계를 거치고 중간 부품을 모으는 더 큰 조립 라인의 일부가 되기도 한다.
    // 한 지점에서 결함이 발생하거나 벨트가 막히게 되면 업스트림에 신호를 보내서 흐름을 제한한다.

    // Reactor Publisher
    // Reactor에서 Mono와 Flux 제공
    // CorePublisher는 reactive streams의 Publisher 를 구현
    // reactive streams 와 호환된다.

    // Flux
    // 0..n개의 item을 subscriber에게 전달 가능
    // subscriber에게 onComplete, onError signal을 전달하면 연결 종료
    // 모든 event가 optional 하기 때문에 다양한 flux 정의 가능. 심지어 onComplete도 optional하다.
    // onComplete를 호출하지 않으면 infinite한 sequence 생성이 가능하다.

    // Mono
    // 0..1개의 item을 subscriber에게 전달한다.
    // subscriber에게 onComplete, onError signal을 전달하면 연결을 종료한다.
    // 모든 event가 optional하다.
    // onNext가 호출되면 곧바로 onComplete 이벤트를 전달한다.
    // Mono<Void> 를 통해서 특정 사건의 완료를 전달 가능하다.


    // Flux는 n개를 처리 가능하다. -> n개가 아니라 1개만 담아서 처리한다면 Mono와 똑같은 역할을 할 수 있는데? -> 그럼.. Mono는 언제 사용하는가?
    // 1. 반드시 하나의 값 혹은 객체를 필요로 하는 경우 -> ex) 유저가 작성한 게시글의 숫자, http 응답 객체
    // 2. 있거나 혹은 없거나 둘 중 하나인 경우 -> ex) 특정 id를 갖는 유저 객체
    // 3. 완료된 시점을 전달해야 하는 경우 -> ex) 유저에게 알림을 보내고 완료된 시점을 전달 받는 경우
    // Publisher에서는 onNext 이후 바로 onComplete를 호출하면 되기 때문에 구현이 더 간단하다.
    // Subscriber의 경우도 최대 1개의 item이 전달된다는 사실을 알고 있기 때문에 더 간결한 코드 작성이 가능해진다.












}