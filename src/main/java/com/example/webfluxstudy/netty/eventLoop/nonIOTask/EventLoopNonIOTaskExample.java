package com.example.webfluxstudy.netty.eventLoop.nonIOTask;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.netty.eventLoop.nonIOTask
 * fileName : EventLoopNonIOTaskExample
 * author : taeil
 * date : 11/15/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/15/23        taeil                   최초생성
 */
@Slf4j
public class EventLoopNonIOTaskExample {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);

        for (int i = 0; i < 10; i++) {
            final int idx = i;

            // 일반적인 Executor 처럼 Non I/O task 수행
            // 하나의 쓰레드에서 돌기 때문에 순서가 보장된다.
            eventLoopGroup.execute(() -> {
                log.info("i : {}", idx);
            });
        }
        eventLoopGroup.shutdownGracefully();

        // eventLoopGroup.execute 을 실행하는 스레드와, new NioEventLoopGroup 의 스레드는 서로 다르다.
        // execute를 실행하면 taskQueue에 먼저 적재한다. -> Queue는 FIFO 구조이기 때문에 순서가 보장된다.
    }
}