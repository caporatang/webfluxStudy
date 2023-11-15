package com.example.webfluxstudy.basicReactiveProgramming.reactorPattern;

/**
 * packageName : com.example.webfluxstudy.reactorPattern
 * fileName : ReactorPattern
 * author : taeil
 * date : 2023/11/01
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/11/01        taeil                   최초생성
 */
public class ReactorPattern {
    // Java NIO non-blocking의 문제점
    // main 쓰레드에서 accept 완료되었는지 주기적으로 확인.
    // 각각의 쓰레드에서 read 가능한지 주기적으로 확인
    // 채널 상태를 수동으로 관리해야 하고 코드 복잡성이 증가
    // 동시에 발생하는 요청이 증가하는 경우, 연결 처리가 순차적으로 발생하여 성능 감소
    // ex)
    //while (true) {
    //    var clientSocket = serverChannel.accept();
    //    if(clientSocket == null) {
    //        Thread.sleep(100);
    //        continue;
    //    }
    //}
    // 이렇게 while로 돌리는것을 busy-wait 이라고 함 -> 콜러가 방해 받지 않고 본인의 일을 할 수 있는데, 콜리 결과가 궁금해서 주기적으로 중간 중간 물어보는 것.
    // busy-wait의 문제
    // 동기 non-blocking에서 주로 발생
    // 루프를 이용해서 원하는 자원을 얻을 때까지 확인
    // 지속적으로 cpu를 점유하기 때문에 cpu 자원이 낭비된다.
    // 확인하는 주기에 따라서 응답 시간 지연이 발생

    // 동기 non-blocking의 원인
    // I/O와 관련된 이벤트를 각각의 쓰레드가 확인한다.
    // 채널의 상태를 수동으로 관리해야 한다.


    // ReactorPattern
    // 동시에 들어오는 요청을 처리하는 이벤트 핸들링 패턴이다.
    // service handler는 들어오는 요청들을 demultiplexing 해서 request handler에게 동기적으로 전달한다.
    // -> 이벤트 관련된 요청들이 준비가 되었는지 체크하고 '하나로' 모으는 부분을 multiplexing 이라고 하고, 반대로 하나로 모인 준비가 된 이벤트들을 핸들러에게 분배하는 과정을 demultiplexing 이라고 한다.
    // accept, read, write 이벤트들을 한 곳에 등록하여 관찰하고, 준비 완료된 이벤트들을 request handler에게 전달한다.
    // -> selector를 이용한 Java nio 처리와 비슷하다.

    // Reactor pattern 구성 요소
    // Reactor : 별도의 쓰레드에서 실행. 여러 요청의 이벤트를 등록하고 감시하며, 이벤트가 준비되면 dispatch 한다.
    // Handler : Reactor로부터 이벤틀르 받아서 처리한다.

    // Reactor 구현
    // 별도의 쓰레드에서 동작해야 한다 -> Runnable을 구현하고 별도의 쓰레드에서 run을 실행한다 -> ex)Executors.singleThread
    // 여러 요청의 이벤트를 등록하고 감시한다 -> Selector를 사용한다.
    // 이벤트가 준비되면 dispatch 한다 -> EventHandler 인터페이스를 만들고 call 해야한다.

    // EventHandler 구현
    // Reactor로부터 이벤트를 받아서 처리 -> accept 이벤트와 read 이벤트 각각을 처리할 수 있는 EventHandler를 만든다.
    // EventHandler의 처리가 Reactor에 영향을 주지 않아야 한다 -> EventHandler에 별도의 쓰레드 실행

    // Reactor pattern
    // Reactor, Selector, Acceptor, EventHandler 구현체로 구성한다.
    // Acceptor는 EventHandler 구현체의 일부. accept 이벤트에만 집중해야 한다.
    // EventHandler 구현체는 read 이벤트에만 집중한다.

    // Http 응답 구조
    // Status Line
    // General Headers
    // Response Headers
    // Entity Headers

    // General Headers : http 요청과 응답 모두에 포함. 일반적인 정보를 제공. 요청 메소드, 버전, 사용자 에이전트 등..
    // Response Headers : http 응답에만 포함. 응답의 메타데이터를 제공. 상태 코드, 캐시 제어 정보, 응답의 유형 등....
    // Entity Headers : 본문과 관련된 정보를 제공. 본문의 유형, 길이, 인코딩 방식 등..
}