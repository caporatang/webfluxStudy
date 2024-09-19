package com.example.webfluxstudy.basicReactiveProgramming.reactorPattern;

/**
 * packageName : com.example.webfluxstudy.reactorPattern
 * fileName : SelectableChannel
 * author : taeil
 * date : 2023/11/01
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/11/01        taeil                   최초생성
 */
public class SelectableChannel {
    // 한 번에 여러 이벤트를 추적할 수 있는 방법..!
    // SelectableChannel
    // configureBlocking과 register 함수 제공
    // register : Selector에 channel을 등록할 수 있다.

    // SocketChannel, ServerSocketChannel 모두 AbstractSelectableChannel을 상속
    // AbstractSelectableChannel은 SelectableChannel을 상속
}