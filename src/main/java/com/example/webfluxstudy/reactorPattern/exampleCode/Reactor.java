package com.example.webfluxstudy.reactorPattern.exampleCode;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class Reactor implements Runnable { // 별도의 스레드에서 동작하게 하기 위함
    // 내부에서는 EventHandler 구현에 대해서는 알 수 없어짐. -> Acceptor 가 밖에서 구현되고 주입만 받기 떄문

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final ServerSocketChannel serverSocket;
    private final Selector selector;
    private final EventHandler acceptor;
    @SneakyThrows
    public Reactor(int port) {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 8080));
        serverSocket.configureBlocking(false);

        acceptor = new Acceptor(selector, serverSocket);

        // attach -> 객체를 같이 동봉해서 넘길수있음
        serverSocket.register(selector, SelectionKey.OP_ACCEPT).attach(acceptor);
    }

    @Override
    public void run() {
        // selector에 등록해서 실행되는 것과 동일하다. -> 별도의 스레드에서 돌려야한다 -> 스레드 추가
        executorService.submit(() -> {
            while (true) {
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();

                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    dispatch(key);
                }
            }
        });
    }

    private void dispatch(SelectionKey selectionKey) {
        // accept 가 들어오고 나면, acceptor가 동봉된 요청을 처리하고, accept의 핸들을 호출 -> accptor에 있는 TCP핸들러를 생성

        EventHandler eventHandler = (EventHandler) selectionKey.attachment();

        if(selectionKey.isReadable() || selectionKey.isAcceptable()) {
            eventHandler.handle();
        }
    }
}
