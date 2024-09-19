package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaAIO;

import ch.qos.logback.core.net.server.Client;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaAIO
 * fileName : AsyncServerSocketCallbacakExample
 * author : taeil
 * date : 2023/10/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/31        taeil                   최초생성
 */
@Slf4j
public class AsyncServerSocketCallbackExample {
    // callback 활용

    @SneakyThrows
    public static void main(String[] args) {
        var serverSocketChannel = AsynchronousServerSocketChannel.open();

        // socket을 반환 받는게 아니라 callback 에게 client socket을 전달하게 된다. accept 되는 시점에는 clientSocket에 접근할 수 없으며, 나중에 콜백에서 접근 가능하다.
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel clientSocketChannel, Object attachment) {
                var requestBuffer = ByteBuffer.allocateDirect(1024);

                // 콜백안에 또 다른 콜백이 있는 구조 -> 가독성이 너무 떨어지는거같은데..~?
                clientSocketChannel.read(requestBuffer, null, new CompletionHandler<Integer, Object>() {
                    @SneakyThrows
                    @Override
                    public void completed(Integer result, Object attachment) {
                        requestBuffer.flip();
                        var request = StandardCharsets.UTF_8.decode(requestBuffer);
                        log.info("request : {}", request);

                        var response = "This is server";
                        var responseBuffer = ByteBuffer.wrap(response.getBytes());
                        clientSocketChannel.write(responseBuffer);
                        clientSocketChannel.close();
                        log.info("end client");
                    }
                    @Override
                    public void failed(Throwable exc, Object attachment) { /* do nothing*/}
                });
            }
            @Override
            public void failed(Throwable exc, Object attachment) { /* do nothing*/ }
        });
    }
}