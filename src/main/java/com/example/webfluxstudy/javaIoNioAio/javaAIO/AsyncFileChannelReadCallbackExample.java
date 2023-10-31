package com.example.webfluxstudy.javaIoNioAio.javaAIO;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaAIO
 * fileName : AsyncFileChannelReadCallbackExample
 * author : taeil
 * date : 2023/10/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/31        taeil                   최초생성
 */
@Slf4j
public class AsyncFileChannelReadCallbackExample {
    // callback 사용

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaAIO/study");

        var channel = AsynchronousFileChannel.open(file.toPath()); // 채널 오픈
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024); // 버퍼 생성

        // 0은 포지션, attachment : failed의 2번째 인자, CompletionHandler
        channel.read(buffer, 0, null, new CompletionHandler<Integer, Object>() {
            @SneakyThrows
            @Override
            public void completed(Integer result, Object attachment) {
                buffer.flip(); // 버퍼를 읽을수 있는 대기 상태로 만듬
                var resultString = StandardCharsets.UTF_8.decode(buffer);
                log.info("result : {}", resultString);
                channel.close();
            }

            @Override
            public void failed(Throwable exc, Object attachment) { /* do nothing */}
        });

        while (channel.isOpen()) {
            // 예제이기 때문에, 메인에서 호출하고 나서 콜백을 통해 결과가 찍히기 때문에 일부러 blocking으로 잠깐의 while로 결과를 찍을 수 있게함 .
            log.info("Reading...");
        }
    }
}