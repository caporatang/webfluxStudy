package com.example.webfluxstudy.netty.eventLoop;

/**
 * packageName : com.example.webfluxstudy.netty.eventLoop
 * fileName : NIOEventLoop
 * author : taeil
 * date : 11/15/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/15/23        taeil                   최초생성
 */
public class NIOEventLoop {
    // EventExecutor, TaskQueue, Selector를 포함한다.
    // EventExecutor : task를 실행하는 쓰레드 풀
    // TaskQueue : task를 저장하는 queue -> eventExecutor 가 죽시 task를 수행하지 않고 taskQueue에 넣은 후, 나중에 꺼내서 처리 가능
    // Selector : I/O Multiplexing을 지원한다.

    // I/O task 와 Non I/O task 로 구분된다.
    // I/O task : register를 통해서 내부의 selector에 channel을 등록하고 I/O 준비 완료 이벤트가 발생하면 channel의 pipeline을 실행
    // Non I/O task : task queue에서 Runnable 등 실행 가능한 모든 종류의 task를 꺼내서 실행

    // 특정 NIOEventLoop 의 I/O task, NIO task의 비율을 설정할 수 있는 ioRatio -> 설정값이 1이라면 I/O task에 1만큼의 시간을 쓰고, 99를 NIO task 에 쓰겠다는 의미이다.
    // ioRatio를 설정하여 각각 task 수행에 얼마나 시간을 쓸지 정할 수 있다.
    // 기본값은 50이다.
    // -> I/O task와 Non I/O task에 가능한한 동일한 시간을 소모한다.
    // -> 100이면 시간을 측정하지 않고 task 수행
}