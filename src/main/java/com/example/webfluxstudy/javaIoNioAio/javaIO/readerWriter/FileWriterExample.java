package com.example.webfluxstudy.javaIoNioAio.javaIO.readerWriter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.readerWriter
 * fileName : FileWriterExample
 * author : taeil
 * date : 2023/10/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/25        taeil                   최초생성
 */
@Slf4j
public class FileWriterExample {

    @SneakyThrows
    public static void main(String[] args) {
        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaIO/readerWriter/FC660MBT");

        var charset = StandardCharsets.UTF_8;

        try (var fis = new FileWriter(file, charset)){
            var content = "FC660MBT 사고싶다ㅏㅏㅏ";
            fis.write(content);
        }

    }
}