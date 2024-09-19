package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaIO;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaIO
 * fileName : JavaIO
 * author : taeil
 * date : 2023/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/24        taeil                   최초생성
 */
public class JavaIO {
    // Java 1.0에서 처음 도입
    // 파일과 네트워크에 데이터를 읽고 쓸 수 있는 API 제공
    // byte 단위로 읽고 쓸 수 있는 stream (InputStream, OutputStream) 제공
    // blocking으로 동작

    // 자바 IO의 한계
    // 1. 동기 blocking으로 동작 -> 어플리케이션이 IO관련 동작을 할떄는 다른 동작을 할 수 없음.

    // 2. 커널 버퍼에 직접 접근 불가. 따라서 메모리 copy가 발생한다.
    // 2-1) hardware에서 값을 읽어오면, disk controller가 DMA를 통해서 커널 버퍼에 값을 복사
    // 2-2) 커널 버퍼에서 jvm 버퍼로 복사 -> 이 과정에서 cpu자원을 소모
    // DiskController에서 Buffer로 다이렉트로 값을 복사하는것은 자원이 필요 없음. -> kernal space에서 JVM의 Buffer로 복사할 때 CPU 자원이 소모된다.

    // 2-3) jvm 버퍼, jvm 메모리에 있기 때문에 gc대상이 되고 이는 또한 cpu자원을 소모한다.
    // Process       {           Kernel space        }        Hardware
    // [Buffer] <--- [Buffer]  <---- [Disk Controller]  <----  [Disk]

    // 3. 동기 blocking으로 동작
    // application이 read를 호출하면, kernel이 응답을 돌려줄때까지, 아무것도 할 수 없다.
    // I/O 요청이 발생할 때마다 쓰레드를 새로 할당하면, 쓰레드를 생성 및 관리하는 비용과 컨텍스트 스위칭으로 인한 cpu자원이 소모된다.

}