package com.example.webfluxstudy.javaIoNioAio.javaIO.outputStream;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.outputStream
 * fileName : OutputStream
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
public class OutputStream {
    // Flushable 인터페이스가 추가 되어 있음 (inputStream에는 없음)
    // -> write시 바로 전송하지 않고 버퍼에 저장한 다음 일정량의 데이터가 모이변 한번에 전달 -> 지금 현재 있는 데이터를 출력하고 바로 다음 로직을 처리해야한다면 flush로 처리.

    // Closable 구현 -> 명시적으로 close 하거나 try - with - resources 사용 가능
    // write : stream으로 데이터를 쓴다.
    // flush : 버퍼의 모든 데이터를 출력하고 비운다.
    // close : stream을 닫고 더 이상 쓰지 않는다.

    // 어떤 destination에 데이터를 쓸 지에 따라 다양한 구현체가 존재한다.
    // FileOutputStreamExample, ByteArrayOutputStreamExample, BufferedOutputStream
}