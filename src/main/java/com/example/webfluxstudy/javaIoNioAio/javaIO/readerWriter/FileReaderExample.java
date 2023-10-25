package com.example.webfluxstudy.javaIoNioAio.javaIO.readerWriter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.readerWriter
 * fileName : FileReaderExample
 * author : taeil
 * date : 2023/10/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/25        taeil                   최초생성
 */
@Slf4j
public class FileReaderExample {

    @SneakyThrows
    public static void main(String[] args) {
        // 한글은 3바이트로 ..!

        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaIO/readerWriter/FC660M");

        var charset = StandardCharsets.UTF_8;

        try (var fis = new FileReader(file, charset)){
            var value = 0;
            while ((value = fis.read()) != -1) {
                log.info("value : {}", (char) value);
            }
        }
    }
}