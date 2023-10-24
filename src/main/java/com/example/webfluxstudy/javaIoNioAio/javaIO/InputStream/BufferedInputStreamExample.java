package com.example.webfluxstudy.javaIoNioAio.javaIO.InputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.InputStream
 * fileName : BufferedInputStreamExample
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
@Slf4j
public class BufferedInputStreamExample {
    // 다른 inputStream과 조합해서 사용한다.
    // 임시 저장 공간인 buffer를 사용한다.
    // 한 번 read를 호출할 때 buffer 사이즈만큼 미리 조회한다.
    // 그 이후 read를 호출할 때 미리 저장한 buffer 데이터를 반환한다. -> 다시 read 호출 시 파일까지 가지 않는다.

    //                              {    BufferedInputStream    }
    // [Java Application] <- read - [Buffer] <- [FileInputStream] <- read - [File]

    @SneakyThrows
    public static void main(String[] args) {
        var file = Paths.get("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaIO/InputStream/data.txt");
        try (var fis = Files.newInputStream(file)) {
                try (var bis = new BufferedInputStream(fis)) {
                    var value = 0;
                    while ((value = bis.read()) != -1) {
                        log.info("value : {}", (char) value);
                    }
                }

            }
        }
}