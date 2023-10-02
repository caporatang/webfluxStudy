package com.example.webfluxstudy.completableFuture;

/**
 * packageName : com.example.webfluxstudy.completableFuture
 * fileName : ExecutorService
 * author : taeil
 * date : 2023/10/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/02        taeil                   최초생성
 */
public class ExecutorService {
    // ExecutorService : 비동기 Non-blocking으로 작업을 할때 별도의 스레드가 필요한 경우가 많다. -> 동적이나 정적으로 설정해서 스레드를 관리 할 수 있음!
    // 1. 스레드 풀을 이용하여 비동기적으로 작업을 실행하고 관리.
    // 2. 별도의 스레드를 생성하고 관리하지 않아도 되므로, 코드를 간결하게 유지 가능
    // 3. 스레드 풀을 이용하여 자원을 효율적으로 관리

    // ExecutorService의 메서드
    // 1. execute : Runnable 인터페이스를 구현한 작업을 스레드 풀에서 비동기적으로 실행한다.
    // 2. submit : Callable 인터페이스를 구현한 작업을 스레드 풀에서 비동기적으로 실행하고, 해당 작업의 결과를 Future<t> 객체로 반환
    // 3. shutdown : ExecutorService를 종료 -> 더 이상 task를 받지 않는다.

    // Executors - ExecutorService 생성 방법
    // 1. newSingleThreadExecutor : 단일 쓰레드로 구성된 스레드 풀을 생성. 한 번에 하나의 작업만 실행한다.
    // 2. newFixedThreadpool : 고정된 크기의 쓰레드 풀을 생성. 크기는 인자로 주어진 n과 동일하다.
    // 3. newCachedThreadPool : 사용 가능한 쓰레드가 없다면 새로 생성해서 작업을 처리하고, 있다면 재사용. -> 쓰레드가 일정 시간 사용되지 않으면 회수
    // 4. newScheduledThreadPool : 스케줄링 기능을 갖춘 고정 크기의 쓰레드 풀을 생성한다. -> 주기적이거나 지연이 발생하는 작업을 실행한다.
    // 5. newWorkStealingPool : work steal 알고리즘을 사용하는 ForkJoinPool을 생성한다.
}