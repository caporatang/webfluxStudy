package com.example.webfluxstudy.reactorPattern.selector;

/**
 * packageName : com.example.webfluxstudy.reactorPattern.selector
 * fileName : Selector
 * author : taeil
 * date : 2023/11/01
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/11/01        taeil                   최초생성
 */
public class Selector {
    // java.nio.channel 패키지
    // 여러 Channel의 이벤트를 등록하고 준비된 이벤트를 모아서 조회 가능
    // select와 selectedKEys 메서드 제공

    // 여러 채널에서 요청이 들어오면 Selector에 모아서 준비된 이벤트만 Thread에 전달하는 형태
    // -> channel ->
    // -> channel ->    Selector  -> [Thread]
    // -> channel ->

    // select.open()을 실행하면 SelectorProvider.provider().openSelector() 가 실행된다.
    // 플랫폼마다 최적화된 구현 제공 -> selectorProvider의 인터페이스를 구현한 객체를 찾아서 알아서 반환 해준다.
    // PollSelectorProvider : Solaris의 poll 시스템 콜을 활용하는 PollSelector 제공
    // EPollSelectorProvider : Linux에서 사용하는 epoll 기반의 EPollSelector 제공
    // KQueueSelectorProvider : macOS / BSD에서 사용하는 kqueue 기반의ㅣㅣ KQueueSelector
    // WindowsSelectorProvider : Windows의 IOCP 기능을 활용한 WindowsSelector 제공
    // -> 일단 selector를 누군가가 최적화된 구현체를 구현해놨고, 그걸 가져다가 쓴다...정도로 이해하자.


    // Selector 등록
    // channel의 register 내부 함수에서 다시 selector의 register 호출
    // channel에 selector와 관심있는 이벤트 등록
    // ex) ServerSocketChannel을 등록 한다면,
    // public final SelectionKey register(Selector sel, int ops) { }
    // ops : 관심있는 이벤트 -> connect, accept, read, write
    // -> Selector 에게 어떤 채널에서 ops로 넘긴 이벤트가 준비 완료되면 스레드에게 알려줘라..!

    // channel 이벤트 흐름
    // serverSocket에서는 accept 이벤트를 selector에 등록
    // clientSocket에서는 read 이벤트를 selector에 등록

    // I/O multiplexing
    // 통신에서 다중화(Multiplexing) : 두 개 이상의 저수준의 채널들을 하나의 고수준의 채널로 통합하는 과정
    // ! : 두 개 이상의 Channel을 하나의 Selector로 통합하는 과정 -> I/O Multiplexing 이라고 표현한다.
    // Selector는 SelectableChannel들의 Multiplexer 이다.

}