package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO.buffer.HeapByteBuffer;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.HeapByteBuffer
 * fileName : HeapByteBuffer
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
public class HeapByteBuffer {
    // JVM heap 메모리에 저장. byte array를 랩핑
    // 커널 메모리에서 복사가 일어나므로 데이터를 읽고 쓰는 속도가 느리다 -> 이 과정에서 임시로 Direct Buffer를 만들기 때문에 성능이 저하된다.
    // gc에서 관리가 되므로 allocate, deallocate가 빠르다.

    // HeapByteBuffer : allocate(), wrap() 함수로 생성한다.
    // isDirect() 로 DirectByteBuffer 와 구분 가능
}