package com.example.webfluxstudy.javaIoNioAio.javaIO.outputStream;

import lombok.SneakyThrows;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.outputStream
 * fileName : BufferedOutputStreamExample
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
public class BufferedOutputStreamExample {
    // 다른 outputStream과 조합해서 사용한다.
    // Buffer를 사용한다.
    // 한 번 write를 호출하면 buffer에만 write 한다.
    // 추후에 flush하여 한번에 outputStream에 write 한다.

    //                                {     BufferedOutputStream   }
    // [Java Application] - write -> [Buffer] -> [FileOutputStream] -> write -> [File]

    @SneakyThrows
    public static void main(String[] args) {
        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaIO/outputStream/dest2.txt");

        // 파일을 outputStream 에 주고
        var fos = new FileOutputStream(file);
        try (var bos = new BufferedOutputStream(fos)) { // FileOutputStream을 BufferedOutputStream에 넘겨줌
            var content = "키보드 더 사고싶다ㅏㅏㅏㅏㅏㅏ";

            bos.write(content.getBytes());
            bos.flush();
        }

    }
    // 이미 OutputStream도 Buffer 를 갖는데, 왜 BufferedStream이 또 존재하는가?
    // BufferedOutputStream 가 더 큰 buffer를 가짐 , -> 더 큰 처리가 가능함.
    // BufferedOutputStream은 명시적으로 flush를 호출하지 않아도 자동으로 일어남.
}