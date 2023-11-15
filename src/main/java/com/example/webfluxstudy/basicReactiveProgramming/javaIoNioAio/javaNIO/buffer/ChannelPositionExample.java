package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO.buffer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer
 * fileName : ChannelPositionExample
 * author : taeil
 * date : 2023/10/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/25        taeil                   최초생성
 */
@Slf4j
public class ChannelPositionExample {

    @SneakyThrows
    public static void main(String[] args) {
        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaNIO/buffer/test.txt");

        // fileChannel 을 열어서 파일에 있는 내용을 가져옴
        try (var fileChannel = FileChannel.open(file.toPath())){
            var byteBuffer = ByteBuffer.allocate(1024);
            logPosition("allocat", byteBuffer);

            // file로부터 값을 읽어서 byteBuffer에 write
            fileChannel.read(byteBuffer);
            logPosition("write", byteBuffer);

            // flip() 을 호출하여 읽기모드로 전환
            byteBuffer.flip();
            logPosition("flip1", byteBuffer);

            // 읽기모드로 전환하여 처음부터 limit (마지막까지 write한 위치까지)까지 읽음
            var result = StandardCharsets.UTF_8.decode(byteBuffer);
            log.info("result : {}", result);
            logPosition("read1", byteBuffer);

            byteBuffer.rewind();
            logPosition("rewind", byteBuffer);

            var result2 = StandardCharsets.UTF_8.decode(byteBuffer);
            log.info("result2 : {}", result2);
            logPosition("read2", byteBuffer);

            var result3 = StandardCharsets.UTF_8.decode(byteBuffer);
            log.info("result3: {}", result3);
            // position이 limit과 동일. -> 바로 종료
            logPosition("read3", byteBuffer);

            byteBuffer.clear();
            logPosition("clear", byteBuffer);

            // 다이렉트 바이트 버퍼
            var directByteBuffer = ByteBuffer.allocateDirect(1024);
            assert directByteBuffer.isDirect(); // -> 다이렉트 버퍼 true

            // 힙 바이트 버퍼
            var heapByteBuffer = ByteBuffer.allocate(1024);
            assert !heapByteBuffer.isDirect(); // -> false

            // wrap 으로 바이트를 바이트 버퍼로 만들수있음 -> 만든 버퍼는 힙 바이트 버퍼임.
            var byteBufferByWrap = ByteBuffer.wrap("hello".getBytes());
            assert !byteBufferByWrap.isDirect();
        }
    }

    private static void logPosition(String action, ByteBuffer byteBuffer) {
        log.info("{}) position: {}, limit: {}, capacity: {}",
                action,
                byteBuffer.position(),
                byteBuffer.limit(),
                byteBuffer.capacity());
    }
}