package com.example.webfluxstudy.basicReactiveProgramming.javaIoNioAio.javaAIO;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaAIO
 * fileName : JavaAIO
 * author : taeil
 * date : 2023/10/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/31        taeil                   최초생성
 */
public class JavaAIO {
    // JavaAIO는 NIO2 라고도 한다.

    // JavaAIO
    // Java 1.7 부터 NIO를 지원한다.
    // AsynchronousChannel을 지원
    // AsynchronousSocketChannel, AsynchronousSocketChannelSocketChannel, AsynchronousSocketChannelFileChannel 등을 지원한다..
    // callback과 future 를 통해 작업 결과를 전달한다.

    // Thread pool과 epoll, kqueue 등의 이벤트 알림 system call을 이용한다.
    // I/O가 준비되었을때, Future, 혹은 callback으로 비동기적인 로직 처리가 가능하다.

    // 1. read 요청 -> 비동기 채널 -> 바로 반환  -> Thread pool에 있는 task queue에 요청을 전달 -> 각각의 Thread 들이 task를 수행한다. -> 수행 결과는 각각의 Thread가 callback으로 돌려준다.
}