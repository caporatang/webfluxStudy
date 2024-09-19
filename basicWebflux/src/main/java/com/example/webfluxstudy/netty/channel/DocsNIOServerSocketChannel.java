package com.example.webfluxstudy.netty.channel;

/**
 * packageName : com.example.webfluxstudy.netty.channel
 * fileName : DocsNIOServerSocketChannel
 * author : taeil
 * date : 11/17/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/17/23        taeil                   최초생성
 */
public class DocsNIOServerSocketChannel {
    // netty 에서는 java nio의 Channel을 사용하지 않고 거의 자체 구현
    // Channel, ServerSocketChannel 모두 자체 구현
    // AbstractChannel: ChannelPipeline을 갖는다.
    // AbstractNioChannel : 내부적으로 java.nio.ServerSocketChannel을 저장하고 register할 때 java nio Selector에 등록 -> Selector와 SelectableChannel을 갖는다.
}