package com.example.webfluxstudy.javaIoNioAio.javaIO.outputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.outputStream
 * fileName : FileOutputStreamExample
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
@Slf4j
public class FileOutputStreamExample {
    // file에 값을 쓸 수 있다.
    // File 객체나 path를 통해서 FileOutputStream을 열 수 있다.
    // application에서 blocking이 일어난다.

    // [Java Application] - write -> [FileOutputStream] - write -> [File]

    @SneakyThrows
    public static void main(String[] args) {
    //    var file = new File(FileOutputStreamExample.class
    //            .getClassLoader()
    //            .getResource("dest.txt").getFile());
        var file = new File("/Users/taeil/study/webfluxStudy/src/main/java/com/example/webfluxstudy/javaIoNioAio/javaIO/outputStream/dest.txt");

        try (var fos = new FileOutputStream(file)) {

            // 파일에 글 쓰기 ~
            var content = "키보드 더 사고 싶다 ^^";

            fos.write(content.getBytes());
            fos.flush();
        }
    }

}