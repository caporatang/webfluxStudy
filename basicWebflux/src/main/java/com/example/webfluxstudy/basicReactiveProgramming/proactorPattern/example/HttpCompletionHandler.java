package com.example.webfluxstudy.basicReactiveProgramming.proactorPattern.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * packageName : com.example.webfluxstudy.proactorPattern.example
 * fileName : HttpCompletionHandler
 * author : taeil
 * date : 11/13/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/13/23        taeil                   최초생성
 */
@Slf4j
public class HttpCompletionHandler implements CompletionHandler<Integer, Void> {
    // 생성자에서 read를 실행하고 스스로를 인자로 전달하여 read I/O 이벤트 완료시 요청 처리
    // read I/O 이벤트 완료시 저장할 buffer를 생성하여 할당한다.

    private final MsgCodec msgCodec;
    private final ByteBuffer requestByteBuffer;
    private final AsynchronousSocketChannel socketChannel;

    public HttpCompletionHandler(AsynchronousSocketChannel socketChannel) {
        this.msgCodec = new MsgCodec();
        this.requestByteBuffer = ByteBuffer.allocateDirect(1024);
        this.socketChannel = socketChannel;
        this.socketChannel.read(this.requestByteBuffer, null, this);
    }


    @Override
    public void completed(Integer result, Void attachment) {
        String requestBody = handleRequest();
        log.info("requestBody : {}", requestBody);
        sendResponse(requestBody);
    }

    @Override
    public void failed(Throwable exc, Void attachment) {
        log.error("Failed to read from client", exc);
    }

    // handleRequest는 Reactor 의 HttpEventHandler와 동일
    // sendResponse 에서는 socketChannel의 write를 이용하여 클라이언트에 응답 전달
    // write완료시 close!
    @SneakyThrows
    private String handleRequest() {
        return msgCodec.decode(requestByteBuffer);
    }

    private void sendResponse(String requestBody) {
        ByteBuffer responseByteBuffer = msgCodec.encode(requestBody);
        socketChannel.write(responseByteBuffer, null, new CompletionHandler<Integer, Object>() {

            @SneakyThrows
            @Override
            public void completed(Integer result, Object attachment) {
                socketChannel.close();
            }

            @SneakyThrows
            @Override
            public void failed(Throwable exc, Object attachment) {
                socketChannel.close();
            }
        });
    }

}