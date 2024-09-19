package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO.buffer.channel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer
 * fileName : SocketChannelExample
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
@Slf4j
public class SocketChannelExample {

    @SneakyThrows
    public static void main(String[] args) {
        try (var socketChannel = SocketChannel.open()){ // socket 채널 오픈
            var address = new InetSocketAddress("localhost", 8080); // 바인딩
            var connected = socketChannel.connect(address); // 커넥팅 -> 커넥팅 될떄까진 블락킹 대기
            log.info("connected : {}", connected );

            String request = "This is client";
            ByteBuffer requestBuffer = ByteBuffer.wrap(request.getBytes()); // 힙 바이트 버퍼
            socketChannel.write(requestBuffer); // 소켓채널에 write
            requestBuffer.clear();

            ByteBuffer res = ByteBuffer.allocateDirect(1024); // 1키로 바이트 버퍼 생성
            while (socketChannel.read(res) < 0) { // 서버에서 응답받은 결과를 버퍼에 작성하자
                res.flip();
                log.info("response {}", StandardCharsets.UTF_8.decode(res));
                res.clear();
            }
        }
    }
}