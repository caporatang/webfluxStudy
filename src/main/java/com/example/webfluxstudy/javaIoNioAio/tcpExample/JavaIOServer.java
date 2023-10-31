package com.example.webfluxstudy.javaIoNioAio.tcpExample;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
public class JavaIOServer {
    // 여러 요청 받기
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        try (ServerSocket serverSocket = new ServerSocket()){
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            while (true) {
                Socket clientSocket = serverSocket.accept();

                byte[] requestBytes = new byte[1024];
                InputStream in = clientSocket.getInputStream();
                in.read(requestBytes);
                log.info("request : {}", new String(requestBytes).trim());

                OutputStream out = clientSocket.getOutputStream();
                String response = "This is server";

                out.write(response.getBytes());
                out.flush();
                log.info("end main");
            }
        }
    }
}