package com.example.webfluxstudy.basicReactiveProgramming.proactorPattern.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

/**
 * packageName : com.example.webfluxstudy.proactorPattern.example
 * fileName : Proactor
 * author : taeil
 * date : 11/13/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/13/23        taeil                   최초생성
 */
@Slf4j
public class Proactor implements Runnable {
    // AsynchronousServerSocketChannel 에 accept를 실행하고 acceptor를 등록
    // Reactor에서 싱글 쓰레드를 이용해서 loop를 실행했지만, Proactor에서 콜백만 등록

    private final AsynchronousServerSocketChannel serverSocketChannel;

    @SneakyThrows
    public Proactor(int port) {
        serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", port));
    }

    @Override
    public void run() {
        var acceptor = new AcceptCompletionHandler(serverSocketChannel);
        serverSocketChannel.accept(null, acceptor);
    }
}