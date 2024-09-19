package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaIO.InputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.InputStream
 * fileName : ByteArrayInputStreamExample
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
@Slf4j
public class ByteArrayInputStreamExample {
    // ByteArrayInputStream
    // byte array로부터 값을 읽을 수 있다.
    // 메모리가 source가 된다. -> JVM 메모리 안에 있는 데이터이기 때문에.
    // [Java Application] <- read - [ByteArrayInputStream] <- read - [ByteArray]

    @SneakyThrows
    public static void main(String[] args) {
        var bytes = new byte[]{100, 101, 102, 103, 104};

        try(var bais = new ByteArrayInputStream(bytes)) {
            var value = 0 ;
            while ((value = bais.read()) != -1) {
                log.info("value : {}", value);
            }

        }

        try(var bais = new ByteArrayInputStream(bytes)) {
            var values = bais.readAllBytes();
            log.info("ALL VALUES : {}", values);

        }

    } // main
}