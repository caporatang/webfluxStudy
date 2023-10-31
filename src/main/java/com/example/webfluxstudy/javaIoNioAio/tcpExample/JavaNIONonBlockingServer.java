package com.example.webfluxstudy.javaIoNioAio.tcpExample;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

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
public class JavaNIONonBlockingServer {

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

                // 논블로킹으로 변경하자 -> 다른 스레드에서 동작 시키고 싶다.
                handleRequest(clientSocket);
            }
        }
    }

    @SneakyThrows
    private static void handleRequest(SocketChannel clientSocket) {
        // 메인 함수 하나 에서만 동작되는 코드를 분리 해보자..
        // 메인 함수 입장에서는 해당 로직이 blocking을 유발하고 있기 때문에 아무 로직없는 1000건의 요청이 2초가 걸리는것임.

        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        while (clientSocket.read(requestByteBuffer) == 0) {
            // 비동기 처리이니, 무조건 값을 준다는 보장이 없음 -> read는 값이 없으면 0을 반환한다. -> 값이 있으면 while을 빠져나옴
            log.info("Reading....");
        }
        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();

        log.info("request : {}", requestBody);

        // 성능 체크용
        Thread.sleep(10);

        ByteBuffer responseByteBuffer = ByteBuffer.wrap("This is server".getBytes());
        clientSocket.write(responseByteBuffer);
        clientSocket.close();
    }
}