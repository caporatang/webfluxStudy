package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaAIO;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaAIO
 * fileName : AsyncServerSocketFutureExample
 * author : taeil
 * date : 2023/10/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/31        taeil                   최초생성
 */
@Slf4j
public class AsyncServerSocketFutureExample {
    // Future 활용
    // 비동기가 아니라 ...동기같다 .... callback보단 가독성은 훨씬 낫다..

    @SneakyThrows
    public static void main(String[] args) {
        var serverSocketChannel = AsynchronousServerSocketChannel.open();
        var address = new InetSocketAddress("localhost", 8080);
        serverSocketChannel.bind(address);

        Future<AsynchronousSocketChannel> clientSocketFuture = serverSocketChannel.accept(); // future 반환

        while (!clientSocketFuture.isDone()) { // future 끝날때까지 대기
            Thread.sleep(100);
            log.info("Waiting...");
        }
        // while문을 빠져 나왔다는 것은 처리가 끝났다 -> clientSocket에 접근이 가능하다
        var clientSocket = clientSocketFuture.get();

        var requestBuffer = ByteBuffer.allocateDirect(1024);
        // 동일하게 Future를 반환하기 떄문에 마찬가지로 while문 체크
        Future<Integer> channelRead = clientSocket.read(requestBuffer);
        while (!channelRead.isDone()) {
            log.info("Reading...");
        }

        // requestBuffer가 읽을 수 있는 상태가 되었기 떄문에 flip
        requestBuffer.flip();
        var request = StandardCharsets.UTF_8.decode(requestBuffer);
        log.info("request : {}", request);

        var response = "This is server.";
        var responseBuffer = ByteBuffer.wrap(response.getBytes());
        clientSocket.write(responseBuffer);
        clientSocket.close();
    }
}