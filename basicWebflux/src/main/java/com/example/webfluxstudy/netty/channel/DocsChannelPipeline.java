package com.example.webfluxstudy.netty.channel;

import io.netty.channel.ChannelPipeline;

/**
 * packageName : com.example.webfluxstudy.netty.channel
 * fileName : DocsChannelPipeline
 * author : taeil
 * date : 11/17/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/17/23        taeil                   최초생성
 */
public class DocsChannelPipeline {
    // Channel I/O 이벤트가 준비되면, EventLoop가 pipeline 실행
    // pipeline에서는 결과로 I/O 작업을 수행한다.

    // channel이 등록된 후, selector를 통해서 I/O 이벤트를 감시하고 있다가 처리할 수 있는 상태가 되었다면
    // channel pipeline 을 통해서 read, inbound I/O event 들을 처리한다. -> 결과를 만들어서 outbound I/O 를 실행해서 Channel에 write..!

    // 1. 이벤트 루프에서 Selector를 통해서 감시
    // 2. 파이프라인으로 이벤트를 전파
    // 3. 파이프라인에서 내부적으로 처리하여 Channel에 write

    // 전파되는 (inboud) I/O 이벤트와 수행하는 (outbound) I/O 작업은 여러 가지 종류가 있지만.
    // 대부분의 경우
    // ChannelPipeline으로 I/O read 이벤트가 전파되고 ChannelPipeline은 I/O write 작업을 수행한다

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
}