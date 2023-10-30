package com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.channel.selectableChannel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.channel.selectableChannel
 * fileName : SocketChannelNonBlockingConnectOnlyExample
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
@Slf4j
public class SocketChannelNonBlockingConnectOnlyExample {
    // SocketChannel - non-Blocking connect

    @SneakyThrows
    public static void main(String[] args) {
        try (var socketChannel = SocketChannel.open()){
            var address = new InetSocketAddress("localhost", 8080);
            socketChannel.configureBlocking(false); // non-blocking 설정

            // 커넥트 하는 시점에 false로 결과가 떨어짐
            var connected = socketChannel.connect(address);
            assert !connected;
        }
    }
}