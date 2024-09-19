package com.example.webfluxstudy.basicReactiveProgramming.ioAspect;

/**
 * packageName : com.example.webfluxstudy.ioAspect
 * fileName : IoAspect
 * author : taeil
 * date : 2023/10/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/02        taeil                   최초생성
 */
public class IoAspect {
    // I/O 관점에서 Blocking/Non-blocking

    // Blocking의 종류
    // 1. blocking은 thread가 오랜 시간 일을 하거나 대기하는 경우 발생
    // 2. CPU-bound blocking : 오랜 시간 일을 하는 경우
    // 3. IO-bound blocking : 오랜 시간 대기 하는 경우

    // CPU-bound blocking
    // 1. thread가 대부분의 시간 CPU 점유
    // 2. 연산이 많은 경우
    // 3. 추가적인 코어를 투입

    // IO-bound blocking
    // 1. thread가 대부분의 시간을 대기
    // 2. 파일 읽기/쓰기, network 요청 처리, 요청 전달 등
    // 3. IO-bound non-blocking이 가능하다.

    // blocking의 전파
    // 1. 하나의 함수에서 여러 함수를 호출하기도 하고, 함수 호출은 중첩적으로 발생한다.
    // 2. callee는 caller가 되고 다시 다른 callee를 호출
    // 3. blocking한 함수를 하나라도 호출한다면 caller는 blocking이 된다.
    // 4. 함수가 non-blocking 하려면 모든 함수가 non-blocking이어야 한다. -> 따라서 I/O bound blocking 또한 발생하면 안된다.

    // System call에 따른 I/O
    // 동기 Blocking I/O
    // 1. recvfrom을 호출
    // 2. blocking socket을 이용해서 read/write 를 수행
    // 3. thread가 block -> (wait queue에서 대기)

    // 동기 Non-blocking I/O
    // 1. recvfrom을 '주기적'으로 호출
    // 2. non-blocking socket을 이용해서 read/write 수행
    // 3. 작업이 완료되지 않았다면 EAGAIN / EWOULDBLOCK 에러 반환

    // I/O
    // Blocking
    // 1. 동기 : Application은 Kernel이 I/O 작업을 완료할 때까지 기다린다. 그 후 결과를 직접 이용해서 이후 본인의 일을 수행
    // 2. 비동기 : 결과적으로 내부 동작이 동기와 동일하게 동작된다.

    // Non-blocking
    // 1. 동기 : Application은 Kernel에 주기적으로 I/O 작업이 완료되었는지 확인한다. 중간중간 본인의 일을 할 수 있고 작업이 완료되면 그 때 본인의 일을 수행한다.
    // 2. 비동기 : Application은 Kernel에 I/O 작업 요청을 보내고 본인의 일을 한다. 작업이 완료되면 Kenel은 signal을 보내거나 callback을 호출한다.
}