package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaIO.outputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.outputStream
 * fileName : SocketOutputStreamExample
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
@Slf4j
public class SocketOutputStreamExample {
    // public이 아니기 떄문에 직접 접근이 불가능하다.
    // socket.getOutputStream으로 접근 가능하다.
    // blocking이 발생한다.

    // [Java Application] - write -> [SocketOutputStream] - write -> [Server]

    @SneakyThrows
    public static void main(String[] args) {
        // 서버 소켓 생성
        ServerSocket serverSocket = new ServerSocket(8080);

        // 클라이언트 리슨 상태로 대기 -> blocking
        Socket clientSocket = serverSocket.accept();

        // 읽어옴
        byte[] buffer = new byte[1024];
        clientSocket.getInputStream().read(buffer);

        // 클라이언트에게 전달하기 위한 값을 쓰기 위한 코드
        var outputStream = clientSocket.getOutputStream(); // 아웃풋 스트림 가져오기.
        var bos = new BufferedOutputStream(outputStream); // outputStream으로 BufferedOutputStream을 열기.
        var bytes = "키보드 사고싶다.".getBytes(); // 해당 문자열을 byte로 바꾸기.
        bos.write(bytes); // 쓰고
        bos.flush(); // 플러시!

        clientSocket.close();
        serverSocket.close();
    }
    // InputStream, OutputStream 은 무조건 byte로 변환을 해서 통신을 해야된다. -> 매우 불편.
}