package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO.buffer.channel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer
 * fileName : ServerSocketChannel
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
@Slf4j
public class ServerSocketChannelExample {

    @SneakyThrows
    public static void main(String[] args) {
        try (var serverChannel = ServerSocketChannel.open()){
            var address = new InetSocketAddress("localhost", 8080);
            serverChannel.bind(address);

            try (var clientSocket = serverChannel.accept()) { // 서버 채널에서 accept -> 클라이언트와 연결 되기 전까지 블락킹 상태로 대기
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                clientSocket.read(buffer);
                buffer.flip();

                var request = new String(buffer.array()).trim();
                log.info("request: {}", request);

                var response = "This is server.";
                var responseBuffer = ByteBuffer.wrap(response.getBytes()); // response로 byte 버퍼 생성
                clientSocket.write(responseBuffer); // 클라이언트에게 넘겨줄 버퍼에 write
                responseBuffer.flip();
            }
        }
    }

}