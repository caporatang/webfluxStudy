package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO.buffer.channel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.directByteBuffer
 * fileName : FileChannelReadExample
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
@Slf4j
public class FileChannelReadExample {
    // FileChannel - read
    // FileInputStream, FileOutputStream 둘 다 FileChannel 하나로 사용 가능!

    @SneakyThrows
    public static void main(String[] args) {
        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaNIO/buffer/channel/hello.txt");

        // try-with-resources
        try (var fileChannel = FileChannel.open(file.toPath())){        // 파일 채널 열기
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);     // 1키로바이트 다이렉트 바이트 버퍼 생성
            fileChannel.read(byteBuffer);                                          // 바이트 버퍼에 fileChannel이 파일 내용을 write

            // 파일을 한번 끝까지 읽고 난 후, 다시 가장 앞으로 position을 0으로 limit을 가장 끝으로 옮겨줌
            byteBuffer.flip();

            var result = StandardCharsets.UTF_8.decode(byteBuffer);
            log.info("result : {}", result);
        }
    }
}