package com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.channel.selectableChannel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.channel.selectableChannel
 * fileName : ServerSocketChannelNonBlockingAcceptOnlyExample
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
@Slf4j
public class ServerSocketChannelNonBlockingAcceptOnlyExample {
    // ServerSocketChannel - non-Blocking accept
    @SneakyThrows
    public static void main(String[] args) {
        try (var serverChannel = ServerSocketChannel.open()){
            var address = new InetSocketAddress("localhost", 8080);
            serverChannel.bind(address);

            // configureBlocking 의 default는 true -> false로 설정 blocking으로 동작
            // 아래에서 accept를 호출하는 순간 바로 다음 라인이 동작된다.
            serverChannel.configureBlocking(false);

            var clientSocket = serverChannel.accept();
            assert clientSocket == null;
        }
    }
}