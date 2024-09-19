package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO.buffer.channel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.directByteBuffer
 * fileName : FileChannelWriteExample
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
@Slf4j
public class FileChannelWriteExample {

    @SneakyThrows
    public static void main(String[] args) {
        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaNIO/buffer/channel/hello.txt");

        // write 는 별도로 인자에 mode를 넘겨주어야 함
        var mode = StandardOpenOption.WRITE;

        try (var fileChannel = FileChannel.open(file.toPath(), mode)){ // 따로 모드를 넘겨주자
            var byteBuffer = ByteBuffer.wrap("키보드 !".getBytes()); // 힙 바이트 버퍼 생성
            var result = fileChannel.write(byteBuffer);
            log.info("result : {}", result);
        }
    }
}