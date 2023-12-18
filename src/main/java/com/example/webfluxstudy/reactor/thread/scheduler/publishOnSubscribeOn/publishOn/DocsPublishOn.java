package com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn.publishOn;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler.publishOnSubscribeOn
 * fileName : PublishOn
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
public class DocsPublishOn {
    // Scheduler를 인자로 받는다
    // publishOn 이후에 추가되는 연산자들의 실행 쓰레드에 영향을 준다.
    // 그 이후 다른 publishOn이 적용되면 추가된 Scheduler로 실행 쓰레드 변경
    // 쓰레드풀 중 하나의 쓰레드만 지속적으로 사용한다.
}