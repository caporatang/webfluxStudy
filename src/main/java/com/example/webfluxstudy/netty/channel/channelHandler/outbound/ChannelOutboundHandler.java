package com.example.webfluxstudy.netty.channel.channelHandler.outbound;

/**
 * packageName : com.example.webfluxstudy.netty.channel.channelHandler
 * fileName : ChannelOutboundHandler
 * author : taeil
 * date : 11/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/18/23        taeil                   최초생성
 */
public class ChannelOutboundHandler {
    // outbound I/O 작업을 가로채서 처리하는 handler

    // ------ 발생할 수 있는 이벤트 목록 ------
    // bind : serverSocketChannel에 bind 요청 시 호출
    // connect : socketChannel이 connect 요청 시 호출
    // disconnect : socketChannel 이 disconnect 요청 시 호출
    // derigister : eventLoop 로부터 deregister 되면 호출

    // read : channel에 대한 read 요청 시 호출
    // write : channel에 대한 write 요청 시 호출. 나중에 작업하는 handler
    // flush : flush 작업이 수행된 경우 호출
    // close : channel이 닫히면 호출

    // 바깥 방향으로 전달된다.
    // write -> flush -> close
}