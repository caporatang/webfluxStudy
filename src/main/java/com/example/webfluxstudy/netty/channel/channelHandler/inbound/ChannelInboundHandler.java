package com.example.webfluxstudy.netty.channel.channelHandler.inbound;

/**
 * packageName : com.example.webfluxstudy.netty.channel.channelHandler
 * fileName : ChannelInboundHandler
 * author : taeil
 * date : 11/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/18/23        taeil                   최초생성
 */
public class ChannelInboundHandler {
    // inbound I/O 이벤트를 처리하기 위한 handler

    // ------- 발생할 수 있는 이벤트 목록 -------
    // channelRegistered : channel이 eventLoop에 등록되는 경우
    // channelUnregistered : channel이 eventLoop 에서 제거되는 경우

    // channelActive : channel이 active 되는 경우
    // channelInactive : channel이 inactive 되고 close 되는 경우

    // channelRead : channel로부터 메세지를 읽을 준비가 된 경우
    // channelReadComplete : channelRead를 통해서 모든 메세지를 읽은 경우

    // userEventTriggered : user event가 트리거된 경우
    // channelWritabilityChanged : channel이 쓸 수 있는 상태가 변경된 경우 . -> 딱 한번 호출

    // 발생할 수 있는 이벤트가 전부 발생 된다면 순서는 다음과 같다
    // 1. channelRegistered (channel이 eventLoop에 등록)
    // 2. channelActive (channel 활성화)
    // 3. channelRead (데이터 읽기)
    // 4. channelReadComplete (데이터 읽기 완료)
    // 5.  channelInactive (채널 비활성화)
    // 6. channelUnregistered (EventLoop 에서 channel 제거)

}