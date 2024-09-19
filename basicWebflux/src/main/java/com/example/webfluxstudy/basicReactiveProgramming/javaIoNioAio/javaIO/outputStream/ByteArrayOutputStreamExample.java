package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaIO.outputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.outputStream
 * fileName : ByteArrayOutputstream
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
@Slf4j
public class ByteArrayOutputStreamExample {
    // byte array에 값을 쓸 수 있다.
    // 메모리가 destination이 된다. -> byte는 JVM에서 관리 되게 때문에 할 때 마다 메모리에 값이 들어감

    @SneakyThrows
    public static void main(String[] args) {
        try (var baos = new ByteArrayOutputStream()){
            baos.write(100);
            baos.write(101);
            baos.write(102);
            baos.write(103);
            baos.write(104);

            var bytes = baos.toByteArray();
            log.info("bytes : {}", bytes);
        }
    }
}