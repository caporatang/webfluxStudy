package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaNIO.buffer.channel.selectableChannel;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer.channel.selectableChannel
 * fileName : SelectableChannel
 * author : taeil
 * date : 2023/10/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/30        taeil                   최초생성
 */
public class SelectableChannel {
    // JavaNIO를 non-blocking 하게 쓰려면?

    // SelectableChannel
    // SocketChannel, ServerSocketChannel 모두 AbstractSelectableChannel을 상속
    // AbstractSelectableChannel은 SelectableChannel을 상속

    // configureBlocking 과 register 함수 제공
    // configureBlocking : serverSocketChannel의 accept, socketChannel의 connect 등이 non-blocking으로 동작한다.
}