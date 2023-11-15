package com.example.webfluxstudy.netty.eventLoop.eventLoopGroup;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.netty.eventLoop.eventLoopGroup
 * fileName : EventLoopGroupNonIOTaskExample
 * author : taeil
 * date : 11/15/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/15/23        taeil                   최초생성
 */
@Slf4j
public class EventLoopGroupNonIOTaskExample {
    // group에서 execute는 eventLoopGroup 내의 eventLoop를 순회하면서 execute를 실행한다.
    // 각각의 eventLoop에 순차적으로 task가 추가되고 실행하기 때문에 eventExecutor 단위로 놓고 보면 순서가 보장된다

    public static void main(String[] args) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(5);
        for (int i = 0; i <12; i++) {
            final int idx = i;
            eventLoopGroup.execute(() -> {
                log.info("i: {}", idx);
            });
        }
        eventLoopGroup.shutdownGracefully();
        // EventLoopGroup에 두개 이상의 그룹에 execute를 하게 되면 하나의 스레드만 일 하는게 아니라 각각의 스레드가 순차적으로 돌면서 taskQueue에 값을 적재한다.
        // -> new NioEventLoopGroup(5); 같은 경우 다섯개의 스레드가 일을 함..

        /*
        nioEventLoopGroup-2 -> 그룹의 이름
        뒤에 붙는 - n -> eventLoop의 아이디
        순서를 보장할 수 있음 -> 처음에 taskQueue에 넣은 애가 당연히 먼저 실행된다
        23:20:53.251 [nioEventLoopGroup-2-4] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 3
        23:20:53.251 [nioEventLoopGroup-2-1] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 0 -> 1번 EventLoop
        23:20:53.251 [nioEventLoopGroup-2-3] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 2
        23:20:53.252 [nioEventLoopGroup-2-3] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 7
        23:20:53.251 [nioEventLoopGroup-2-5] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 4
        23:20:53.252 [nioEventLoopGroup-2-1] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 5 -> 1번 EventLoop
        23:20:53.253 [nioEventLoopGroup-2-5] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 9
        23:20:53.253 [nioEventLoopGroup-2-1] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 10 -> 1번 EventLoop
        23:20:53.252 [nioEventLoopGroup-2-4] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 8
        23:20:53.250 [nioEventLoopGroup-2-2] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 1
        23:20:53.254 [nioEventLoopGroup-2-2] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 6
        23:20:53.255 [nioEventLoopGroup-2-2] INFO com.example.webfluxstudy.netty.eventLoop.eventLoopGroup.EventLoopGroupNonIOTaskExample - i: 11
        */
    }
}