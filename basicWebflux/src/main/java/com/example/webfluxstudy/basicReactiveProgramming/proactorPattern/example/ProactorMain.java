package com.example.webfluxstudy.basicReactiveProgramming.proactorPattern.example;

/**
 * packageName : com.example.webfluxstudy.proactorPattern.example
 * fileName : ProactorMain
 * author : taeil
 * date : 11/13/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/13/23        taeil                   최초생성
 */
public class ProactorMain {
    public static void main(String[] args) {
        // handleRequest는 Reactor의 HttpEventHandler 와 동일
        // sendResponse에서는 socketChannel 의 write를 이용하여 클라이언트에 응답 전달
        // write 완료 시 close를 하여 연결 종료

        // accept 이벤트가 발생 하지 않거나 완료되지 않은 경우에 sleep
        new Proactor(8080).run();
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Reactor를 사용하는게 성능이 더 나을 수 있음 -> netty에서도 AIO를 도입하려다가 Selector 로 충분하다고 판단해서 도입하지 않음
}
