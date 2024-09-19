package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaIO.InputStream.socket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.InputStream.socket
 * fileName : SocketInputStream
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
@Slf4j
public class SocketInputStream {
    // SocketInputStream은 public이 아니기 때문에 직접 접근이 불가능하다.
    // socket.getInputStream 으로만 접근 가능하다.
    // blocking이 발생한다.

    @SneakyThrows
    public static void main(String[] args) {
        // 서버 소켓 생성
        ServerSocket serverSocket = new ServerSocket(8080);

        // 클라이언트 접속 대기 -> listen 상태로 blocking
        Socket clientSocket = serverSocket.accept();

        var inputStream = clientSocket.getInputStream();

        // 클라이언트로부터 데이터를 읽어들임
        try (BufferedInputStream bis = new BufferedInputStream(inputStream)) { // bufferedInputStream은 inputStream 즉, 담고있는 Stream도 close가 되기 떄문에 client측 Stream도 close..!
            // 1KB 크기의 버퍼 생성
            byte[] buffer = new byte[1024];
            int bytesRead = bis.read(buffer);

            String inputLine = new String(buffer, 0, bytesRead);

            log.info("bytes : {}", inputLine);
        }
        clientSocket.close();
        serverSocket.close();
    }
}