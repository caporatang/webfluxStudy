package com.example.webfluxstudy.javaIoNioAio.tcpExample;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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
public class JavaNIONonBlockingMultiServer {
    private static AtomicInteger count = new AtomicInteger(0); //-> 결과 출력 용
    // 꼭 안써도 되지만, multiClient 처럼 성능을 개선 시킬수있음 ..! -> 기본 common 풀을 사용하는것보다 아주 약간 성능을 개선할 수 있음 ..!
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);


    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()){
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            serverSocket.configureBlocking(false); // 비동기로 바꿔주자

            while (true) {

                // configureBlocking 옵션을 false로 주는 경우 accept의 반환은 null이 반환되기 떄문에 기존과 동일한 로직을 사용하면 NPE가 발생된다.
                SocketChannel clientSocket = serverSocket.accept();
                if(clientSocket == null) {
                    // 클라이언트 소켓이 null이 아닐때까지 슬립!
                    Thread.sleep(100);
                    // log.info("wait accept !!");
                    continue;
                }

                // 논블로킹으로 변경하자 -> 다른 스레드에서 동작 시키고 싶다. -> CompletableFuture를 사용해서 별도의 스레드에서 처리하자.
                CompletableFuture.runAsync(() -> {
                    // runAsync로 handleRequest의 로직이 끝나지 않아도 다음 요청을 받게하자..~
                    handleRequest(clientSocket);
                }, executorService);
            }
        }
    }

    @SneakyThrows
    private static void handleRequest(SocketChannel clientSocket) {

        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        while (clientSocket.read(requestByteBuffer) == 0) {
            // 비동기 처리이니, 무조건 값을 준다는 보장이 없음 -> read는 값이 없으면 0을 반환한다. -> 값이 있으면 while을 빠져나옴
            log.info("Reading....");
        }
        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();

        log.info("request : {}", requestBody);

        // 성능 체크용 -> 1000개의 요청에 대한 처리가 12초 걸리던것이..~ 2초로 줄어들음 -> 각각의 스레드로 바로바로 요청을 받기 때문임
        Thread.sleep(10);

        ByteBuffer responseByteBuffer = ByteBuffer.wrap("This is server".getBytes());
        clientSocket.write(responseByteBuffer);
        clientSocket.close();

        int c = count.incrementAndGet();
        log.info("count : {}", c);
    }
}