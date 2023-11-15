package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO
 * fileName : JavaNIO
 * author : taeil
 * date : 2023/10/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/25        taeil                   최초생성
 */
public class JavaNIO {
    // JavaIO의 한계점을 극복하기 위해 도입

    // java New Input / Output -> Non-blocking이 아님
    // java 1.4에서 처음 도입
    // 파일과 네트워크에 데이터를 읽고 쓸 수 있는 API 제공
    // buffer 기반 -> JavaIO는 Byte 기반
    // non-blocking 지원
    // selector, channel 도입으로 높은 성능 보장

    // Java NIO vs Java IO
    //                  Java NIO             Java IO
    // 데이터의 흐름        양방향                단방향
    // 종류               Channel             InputStream, OutputStream
    // 데이터의 단위        buffer              byte 혹은 character
    // blocking여부     non-blocking지원       blocking만 가능
    // 특이사항           Selector 지원

}