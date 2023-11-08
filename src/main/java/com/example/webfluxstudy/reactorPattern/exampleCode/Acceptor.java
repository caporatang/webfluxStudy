package com.example.webfluxstudy.reactorPattern.exampleCode;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * packageName : com.example.webfluxstudy.reactorPattern.exampleCode
 * fileName : Acceptor
 * author : taeil
 * date : 11/8/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/8/23        taeil                   최초생성
 */
@Slf4j
@RequiredArgsConstructor
public class Acceptor implements EventHandler{
    //
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    @SneakyThrows
    @Override
    public void handle() {
        SocketChannel clientSocket = serverSocketChannel.accept();
        new TcpEventHandler(selector, clientSocket);
    }
}