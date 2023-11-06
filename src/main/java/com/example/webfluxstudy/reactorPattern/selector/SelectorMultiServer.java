package com.example.webfluxstudy.reactorPattern.selector;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.tcpExample
 * fileName : JavaIOSingleServer
 * author : taeil
 * date : 2023/10/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/31        taeil                   최초생성
 */
@Slf4j
public class SelectorMultiServer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        try (ServerSocketChannel serverSocket = ServerSocketChannel.open();
             Selector selector = Selector.open();
        ){
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                // selector를 사용함으로서 busy wait 로직 삭제 가능 -> 준비된 이벤트가 없다면 blocking
                selector.select();

                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();

                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove(); // 중복해서 이벤트를 처리하려는 동작을 막기위해 바로 remove

                    if (key.isAcceptable()) {
                        // isAcceptable --> 서버 소켓 채널이 accept 가 가능하다는 것이고, 논블록킹임에도 서버 소켓 채널에 접근 하지 못할 걸, 걱정하지 않아도 된다.
                        SocketChannel clientSocket = ((ServerSocketChannel) key.channel()).accept();
                        clientSocket.configureBlocking(false);
                        clientSocket.register(selector, SelectionKey.OP_READ); // Read
                    } else if(key.isReadable()) {
                        // Read 이벤트가 준비 되었는지 체크 -> 기존 로직에서는 while문을 돌면서 buffer를 체크했지만 이벤트를 감시해서 처리한다 -> 훨씬 간결하고 편하게!
                        SocketChannel clientSocket = ((SocketChannel) key.channel());
                        String requestBody = handleRequest(clientSocket);
                        sendResponse(clientSocket, requestBody);
                    }
                }
            }
        }
    }

    @SneakyThrows
    private static String handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        while (clientSocket.read(requestByteBuffer) == 0) {
            log.info("Reading....");
        }
        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();

        log.info("request : {}", requestBody);

//        // 성능 체크용
//        Thread.sleep(10);
//
//        ByteBuffer responseByteBuffer = ByteBuffer.wrap("This is server".getBytes());
//        clientSocket.write(responseByteBuffer);
//        clientSocket.close();
        return requestBody;
    }


    @SneakyThrows
    private static void sendResponse(SocketChannel clientSocket, String requestBody) {

        CompletableFuture.runAsync(() -> {
            // runAsync로 돌려보자
            try {
                Thread.sleep(10);

                String content = "received: " + requestBody;

                ByteBuffer responseByteBuffer = ByteBuffer.wrap(content.getBytes());
                clientSocket.write(responseByteBuffer);
                clientSocket.close();
            } catch (Exception e) {
            }
        }, executorService);
    }
}