package com.example.webfluxstudy.reactorPattern;

/**
 * packageName : com.example.webfluxstudy.reactorPattern
 * fileName : ReactorPattern
 * author : taeil
 * date : 2023/11/01
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/11/01        taeil                   최초생성
 */
public class ReactorPattern {
    // Java NIO non-blocking의 문제점
    // main 쓰레드에서 accept 완료되었는지 주기적으로 확인.
    // 각각의 쓰레드에서 read 가능한지 주기적으로 확인
    // 채널 상태를 수동으로 관리해야 하고 코드 복잡성이 증가
    // 동시에 발생하는 요청이 증가하는 경우, 연결 처리가 순차적으로 발생하여 성능 감소
    // ex)
    //while (true) {
    //    var clientSocket = serverChannel.accept();
    //    if(clientSocket == null) {
    //        Thread.sleep(100);
    //        continue;
    //    }
    //}
    // 이렇게 while로 돌리는것을 busy-wait 이라고 함 -> 콜러가 방해 받지 않고 본인의 일을 할 수 있는데, 콜리 결과가 궁금해서 주기적으로 중간 중간 물어보는 것.
    // busy-wait의 문제
    // 동기 non-blocking에서 주로 발생
    // 루프를 이용해서 원하는 자원을 얻을 때까지 확인
    // 지속적으로 cpu를 점유하기 때문에 cpu 자원이 낭비된다.
    // 확인하는 주기에 따라서 응답 시간 지연이 발생

    // 동기 non-blocking의 원인
    // I/O와 관련된 이벤트를 각각의 쓰레드가 확인한다.
    // 채널의 상태를 수동으로 관리해야 한다.
}