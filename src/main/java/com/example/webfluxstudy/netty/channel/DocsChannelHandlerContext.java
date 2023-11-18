package com.example.webfluxstudy.netty.channel;

/**
 * packageName : com.example.webfluxstudy.netty.channel
 * fileName : DocsChannelHandlerContext
 * author : taeil
 * date : 11/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/18/23        taeil                   최초생성
 */
public class DocsChannelHandlerContext {
    /*
     *   ChannelPipeline 내부 구조
     *   pipeline은 ChannelHandlerContext의 '연속'으로 되어 있다
     *   Head context와 Tail context는 기본적으로 포함된다.
     *   각각의 Context는 LinkedList의 형태로 next, prev를 통해서 이전 혹은 다음 context에 접근한다.
     *
     *
     *   모든 inbound I/O 이벤트는 next로..
     *   모든 outbound I/O 작업은 prev로..
     *   inbound I/O 이벤트 전파 -> Head Context -> context1 -> context2 -> context3 -> context4 -> Tail Context  <- outbound I/O 작업 수행
     * */

    // ChannelHandlerContext 내부
    // ChannelHandlerContext는 EventExecutor와 ChannelHandler를 포함 -> inbound에 해당하는 channelHanlder와 outbound에 해당하는 channelhandler를 포함하고 있다.
    // ChannelHandler는 I/O 이벤트를 받아서
    // -> 다음 context에게 넘겨줄 수도 있고
    // -> 다음 context에게 넘겨주지 않고 I/O 작업을 수행할 수도 있다.

    // ChannelHandlerContext 에서 별도의 EventExecutor를 지원하는 이유?
    // ChannelHanlder 에서 시간이 오래 걸리는 연산을 진행한다면 ? -> blocking
    // -> EventLoop 쓰레드에서 해당 ChannelHandler에서 blocking -> 즉, EventLoop에 있는 쓰레드가 blocking 된다는 의미이다.
    // -> EventLoop에 등록된 다른 Channel의 I/O 처리 또한 blocking
    // -> 해당 ChannelHanlder 에서는 EventLoop 쓰레드가 아닌 다른 쓰레드 풀을 사용한다면?

    // 이를 위해서 ChannelHandlerCOntext에 등록된 EventExecutor가 있다면
    // -> next context가 다른 쓰레드풀에서 동작해야하는구나 라고 판단한다.
    // -> 판단 결과에 따라 직접 이벤트 처리를 호출하지 않고 executor.execute로 taskQueue에 넣고 EventLoop 쓰레드는 복귀한다. -> 다른 쓰레드 풀을 활용한다.
}