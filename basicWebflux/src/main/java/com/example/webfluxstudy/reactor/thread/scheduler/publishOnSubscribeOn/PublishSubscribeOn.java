package com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn
 * fileName : PublishSubscribeOn
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
public class PublishSubscribeOn {
    // publishOn은 위치가 중요하고 subscribeOn은 위치가 중요하지 않다.
    // A -> B -> C 순으로 연산자가 chaining 되어 있다면 별도의 publishOn이 없다면, A를 실행한 쓰레드가 B를 실행하고, B를 실행한 쓰레드가 C를 실행하는 등 쓰레드도 chaining 된다.
    // source가 실행되는 쓰레드를 설정할 수 있고 (subscribeOn), 중간에서 실행 쓰레드를 변경할 수 있다 (publishOn)
}