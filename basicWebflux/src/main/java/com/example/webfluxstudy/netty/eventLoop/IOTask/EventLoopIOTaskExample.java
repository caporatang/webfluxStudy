package com.example.webfluxstudy.netty.eventLoop.IOTask;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * packageName : com.example.webfluxstudy.netty.eventLoop.IOTask
 * fileName : EventLoopIOTaskExample
 * author : taeil
 * date : 11/15/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/15/23        taeil                   최초생성
 */
@Slf4j
public class EventLoopIOTaskExample {
    // NIO EventLoop 의 I/O task
    // NIOEventLoop 을 직접 생성할 수 없기 때문에 NIOEventLoopGroup 를 사용한다.
    // I/O 이벤트 완료시 channel의 pipeLine을 실행한다.

    public static void main(String[] args) {
        var channel = new NioServerSocketChannel();
        // 하나의 그룹에 여러개의 NIOEventLoop가 존재할수있음 -> 인자로 1을 넘겼기 떄문에 이 상태는 하나의 이벤트 루프가 존재함.
        var eventLoopGroup = new NioEventLoopGroup(1);

        // NIO 를 수행하는 ServerSocketChannel을 생성하고 accept network I/O 이벤트를 eventLoop에 등록한다.
        // 하나의 eventLoopGroup에 여러 개의 channel 등록이 가능하다. -> 여러 요청이 들어와도 처리할 수 있는 형태가 된다.
        eventLoopGroup.register(channel);

        channel.bind(new InetSocketAddress(8080))
                .addListener(future -> {
                    if(future.isSuccess()) {
                        log.info("Server biund to port 8080");
                    } else {
                        log.info("Failed to bind to port 8080");
                        eventLoopGroup.shutdownGracefully();
                    }
                });
    }

}