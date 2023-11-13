package com.example.webfluxstudy.proactorPattern.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * packageName : com.example.webfluxstudy.proactorPattern.example
 * fileName : AcceptCompletionHandler
 * author : taeil
 * date : 11/13/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/13/23        taeil                   최초생성
 */
@Slf4j
@RequiredArgsConstructor
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {
    // 요청이 들어왔을 때 동작할것임.
    // I/O accept 이벤트가 완료되었을때 호출
    // accept를 한번 더 호출하고 스스로를 인자로 전달하여 다음 요청을 준비한다.
    // HttpCompletionHandler를 생성한다.

    private final AsynchronousServerSocketChannel serverSocketChannel;

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
        // accept를 한번 더 실행하는 이유 : 일종의 콜백, 한번만 호출하고 끝나면 하나의 요청에 대해서만 처리하게 된다
        // 자기 자신을 연결될 때마다 계속 등록을 하면서 연결에 대한 처리를 구현 -> 일종의 loop
        serverSocketChannel.accept(null, this);

        new HttpCompletionHandler(socketChannel);
    }

    @Override
    public void failed(Throwable exc, Void attachment) {
    log.error("Failed to accept connection", exc);
    }
}