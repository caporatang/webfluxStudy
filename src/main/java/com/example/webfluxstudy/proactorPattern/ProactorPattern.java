package com.example.webfluxstudy.proactorPattern;

/**
 * packageName : com.example.webfluxstudy.proactorPattern
 * fileName : ProactorPattern
 * author : taeil
 * date : 11/13/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/13/23        taeil                   최초생성
 */
public class ProactorPattern {
    // Reactor의 문제점
    // Reactor에서 단일 쓰레드와 Selector를 통해서 이벤트 처리 -> Selector 처리하는 객체가 하나라는게 문제다.
    // 감당하기 힘든 트래픽이 부여되거나 이벤트 처리에 오랜 시간이 걸리는 경우 전체 시스템에 영향을 줄 수 있다.
    // Event ---->
    // Event ---->   Selector (Reactor)
    // Event ---->

    // Proactor pattern
    // I/O 이벤트를 시스템(커널)에 직접 등록하고 이벤트 완료시 시스템이 직접 완료 안내까지 한다. -> Reactor는 Selector 가 실행하고, 핸들러에게 전달한다
    // 등록된 콜백을 실행하여 I/O 이벤트를 처리한다.
    // Reactor 에서는 특정 부분이 병목이 되었지만, Proactor에서는 단일 쓰레드, Selector와 같은 병목 지점이 없다.

    // I/O 이벤트 완료를 전달하는 주체가 누구인가?
    // Reactor : Selector
    // Proactor : 커널
}