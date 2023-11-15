package com.example.webfluxstudy.basicReactiveProgramming.reactorPattern.exampleCode;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * packageName : com.example.webfluxstudy.reactorPattern.exampleCode
 * fileName : TcpEventHandler
 * author : taeil
 * date : 11/8/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/8/23        taeil                   최초생성
 */
@Slf4j
public class TcpEventHandler implements EventHandler{
    private ExecutorService executorService = Executors.newFixedThreadPool(50);
    private final SocketChannel clientSocket;

    @SneakyThrows
    public TcpEventHandler (Selector selector , SocketChannel clientSocket) {
        this.clientSocket = clientSocket;
        this.clientSocket.configureBlocking(false);
        this.clientSocket.register(selector, SelectionKey.OP_READ).attach(this);
    }

    @Override
    public void handle() {
        log.info("start handle");
        // handle 은 요청이 완료된 시점이기 떄문에, 소켓 채널로부터 값을 무조건 읽어와도 된다.
        String requestBody = handleRequest(this.clientSocket);
        sendResponse(this.clientSocket, requestBody);
    }


    @SneakyThrows
    private String handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        clientSocket.read(requestByteBuffer);

        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("request: {}", requestBody);

        return requestBody;
    }

    @SneakyThrows
    private void sendResponse(SocketChannel clientSocket, String requestBody) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10);

                String content = "received: " + requestBody;
                ByteBuffer responeByteBuffer = ByteBuffer.wrap(content.getBytes());
                clientSocket.write(responeByteBuffer);
                clientSocket.close();
            } catch (Exception e) { }
        }, executorService);
    }

}