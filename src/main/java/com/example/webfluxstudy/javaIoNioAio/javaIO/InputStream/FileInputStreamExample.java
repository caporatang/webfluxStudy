package com.example.webfluxstudy.javaIoNioAio.javaIO.InputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.InputStream
 * fileName : FileInputStream
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
@Slf4j
public class FileInputStreamExample {
    // file로부터 byte 단위로 값을 읽을 수 있다.
    // File 객체나 path를 통해서 FileInputStream을 열 수 있다.
    // application에서 blocking 이 일어난다.
    // [Java Application] <- read - [FileInputStream] <- read - [File]

    @SneakyThrows
    public static void main(String[] args) {
        var file = FileInputStreamExample.class
                .getClassLoader()
                .getResource("/com/example/webfluxstudy/javaIoNioAio/javaIO/InputStream/data.txt")
                .getFile();


        try (var fis = new java.io.FileInputStream(file)){
            var value = 0;

            while ((value = fis.read()) != -1) {
                log.info("value: {}", (char) value);
            }
        }
//        var file = Paths.get("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaIO/InputStream/data.txt");
//        try (var fis = Files.newInputStream(file)) {
//            var value = 0;
//
//            while ((value = fis.read()) != -1) {
//                log.info("value: {}", (char) value);
//            }
//        }




    }
}