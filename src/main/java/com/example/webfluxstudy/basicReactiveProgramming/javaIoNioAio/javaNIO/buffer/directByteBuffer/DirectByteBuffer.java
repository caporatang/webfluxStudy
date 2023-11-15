package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO.buffer.directByteBuffer;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.directByteBuffer
 * fileName : DirectByteBuffer
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
public class DirectByteBuffer {
    // native 메모리(off-heap)에 저장
    // 커널 메모리에서 복사를 하지 않으므로 데이터를 읽고 쓰는 속도가 빠르다.
    // 비용이 많이 드는 system call을 사용하므로 allocate, deallocate가 느리다

    // DirectByteBuffer : allocateDirect() 함수로 생성 가능하다
    // isDirect() 로 HeapByteBuffer와 구분 가능
}