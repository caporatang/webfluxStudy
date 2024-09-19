package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaAIO;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaAIO
 * fileName : AsyncFileChannelReadFutureExample
 * author : taeil
 * date : 2023/10/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/31        taeil                   최초생성
 */
@Slf4j
public class AsyncFileChannelReadFutureExample {

    @SneakyThrows
    public static void main(String[] args) {
        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaAIO/study");

        try (var channel = AsynchronousFileChannel.open(file.toPath())) {
            var buffer = ByteBuffer.allocateDirect(1024);

            // read 하면서 buffer를 넘김 ->
            // callback 방식에서는 attachment, CompletionHandler 즉, callback 을 같이 넘겼다면 Future 방식의 차이가 존재함
            Future<Integer> channelRead = channel.read(buffer, 0);
            while (!channelRead.isDone()) {
                // Future를 이용해서 결과를 받을것이기 때문에 끝났는지 계속해서 체크..
                // 제일 큰 문제점은 동기적으로 동작한다는 단점이 있음 -> channel.read 의 결과에 관심이 있기 때문에..어쩔수 없이 동기적으로 동작함.
                log.info("Reading");
            }
            // 끝났다면 buffer를 읽을 수 있는 상태로 만듦
            buffer.flip();
            var result = StandardCharsets.UTF_8.decode(buffer);
            log.info("result : {}", result);
        }
    }
}