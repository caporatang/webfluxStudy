package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaIO.InputStream;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO.InputStream
 * fileName : InputStream
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
public class InputStream {
    // Closable 구현, 명시적으로 close하거나 try - with - resources 사용 가능
    // read : stream으로 데이터를 읽고, 읽은 값을 반환, -1 이라면 끝에 도달했다는 것을 의미한다.
    // close : stream을 닫고 더 이상 데이터를 읽지 않는다.

    // 어떤 source로부터 데이터를 읽을지에 따라 다양한 구현체가 존재한다.
    // FileInputStream, ByteArrayInputStream, BufferedInputStream..

    // 가장 큰 기준은 어떤 데이터 소스로부터 데이터를 읽을지다.
    // ex) File -> 파일에서 , Buffer -> 버퍼


}