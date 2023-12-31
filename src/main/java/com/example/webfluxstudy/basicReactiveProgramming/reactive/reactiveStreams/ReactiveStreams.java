package com.example.webfluxstudy.basicReactiveProgramming.reactive.reactiveStreams;

/**
 * packageName : com.example.webfluxstudy.reactiveStreams
 * fileName : ReactiveStreams
 * author : taeil
 * date : 2023/10/17
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/17        taeil                   최초생성
 */
public class ReactiveStreams {
    // reactive란? (무언가를 바꾸거나 예방하기 위해) 먼저 행동하기 보다는 사건이나 상황에 반응하는 것.

    // Reactive streams의 역사
    // 2011년 6월 : MS 닷넷 프레임워크를 위한 Reactive Extensions 배포
    // 2015년 4월 : reactive streams 1.0.0 버전이 java 9와 함꼐 배포
    // 2015년 4월 : akka stream 1.0 배포
    // 2017년 8월 : reactive streams 1.0.1 배포
    // 2021년 7월 : red hat의 munity 1.0 배포

    // Reactive manifesto : 소프트웨어 아키텍쳐에 대한 선언문, Reactive streams의 특성을 갖오하고 구축에 필요한 핵심 가치를 4가지로 제시하고 가이드라인을 제공함
    // 1. Responsive(응답성) : 일관성 있게 응답을 빠르게 내려주는 시스템을 구성하자.
    // 1-1) 요구사항 : 문제를 신속하게 탐지하고 효과적으로 대처, 신속하고 일관성 있는 응답 시간 제공, 신뢰할 수 있는 '상한선'을 설정하여 일관된 서비스 품질을 제공
    // 1-2) 요구사항에 대한 결과 : 가능한 한 즉각적으로 응답, 사용자의 편의성과 유용성의 기초, 오류 처리를 단순화, 일반 사용자에게 신뢰를 조성하고, 새로운 상호작용을 촉진

    // 2. Resilient(복원력) :
    // 2-1) 요구사항 : 복제, 봉쇄, 격리, 위임에 의해 실현
    //               장애는 각각의 구성 요소에 포함 (봉쇄)
    //               구성 요소들은 서로 분리 (격리)
    //               복구 프로세스는 다른(외부의) 구성 요소에 위임 (위임)
    //               필요한 경우 복제를 통해 고가용성이 보장 (복제)
    // 2-2) 결과 : 장애에 직면하더라도 응답성을 유지, 시스템이 부분적으로 고장이 나더라도, 전체 시스템을 위험하게 하지 않고 복구 할 수 있도록 보장, 구성 요소의 클라이언트는 장애를 처리하는데에 압박이 없음

    // 3. Elastic (유연성)
    // 3-1) 요구사항 : 경쟁하는 지점이나 중앙 집중적인 병목 현상이 존재하지 않도록 설계
    //               구성 요소를 샤딩하거나 복제하여 입력을 분산
    //               실시간 성능을 측정하는 도구를 제공
    //               응답성 있고 예측 가능한 규모 확장 알고리즘을 지원
    // 3-2) 결과 : 작업량이 변화하더라도 응답성을 유지, 입력 속도의 변화에 따라 이러한 입력에 할당된 자원을 증가시키거나 감소, 상품 및 소프트웨어 플랫폼에 비용 효율이 높은 방식으로 유연성을 제공

    // 4. Message Driven (메세지 기반)
    // 4-1) 요구사항 : 비동기 메시지 전달에 의존, 명시적인 메시지 전달, 위치 투명 메시징을 통신 수단으로 사용, 논블로킹 통신
    // 4-2) 결과 : 구성 요소 사이에서 느슨한 결합, 격리, 위치 투명을 보장하는 경계를 형성, 이 경계는 장애를 메시지로 지정하는 수단을 제공한다.
    //            시스템에 메시지 큐를 생성하고, 모니터링하며 필요시 배압을 적용
    //            유연성을 부여하고, 부하 관리와 흐름제어를 가능하게
    //            단일 호스트든 클러스터를 가로지르든 동일한 구성과 의미를 갖고 장애를 관리
    //            수신자가 활성화가 되어 있을 때만 자원을 소비할 수 있기 때문에 시스템 부하를 억제

    // 4-2 결과에 대한 정리 요약
    // 1. 비동기 통신 : 구성 요소는 서로 비동기적으로 메시지를 주고 받으며, 독립적인 실행을 보장한다. -> caller가 callee 에게 요청을 보낼때 결과를 기다리지 않고 바로 바로 비동기로 처리 되야한다
    // 2. 메시지 큐 : 메시지 큐를 생성하고 배압을 적용하여 부하를 관리하고 흐름을 제어한다.   -> 비동기적으로 통신을 메시지 큐를 사용해야 한다는 말이고, 배압을 적용한다는 것은 caller와 callee 사이에 메시지 큐를 사용해서 중간에 버퍼를 둔다는 뜻이다.
    // 3. 복원력 : 구성 요소 사이에 경계를 형성하여 직접적인 장애의 전파를 막고 장애를 메시지로 지정해서 위치와 상관 없이 동일하게 장애를 관리한다.
    // 4. 유연성 : 구성 요소 사이에 경계를 형성하여 각각의 구성 요소를 독립적으로 확장 가능하게 만들고, 자원을 더 쉽게 추가하거나 제거한다.

    // Reactive manifesto 정리 -> 가이드라인 .. 메시지 드라이븐 하지 않더라도 충분히 다른 요소를 확보할 수 있음
    // 핵심 가치 : 가능한 한 즉각적으로 응답
    // 첫 번째 형태 : 장애에 직면하더라도 응답성을 유지
    // 두 번째 형태 : 작업량이 변화하더라도 응답성을 유지
    // 방법 : 비동기 non-blocking 기반의 메시지 큐를 사용해서 통신한다.

    // Reactive stream 구조
    // 데이터 혹은 이벤트를 제공하는 Publisher
    // 데이터 혹은 이벤트를 제공받는 Subscriber
    // 데이터 흐름을 조절하는 Subscription

    // publisher는 subscriber에게 subscription을 전달한다.
    // Subscription 안에는 요청의 갯수를 받을수 있는지에 대한 request 메서드와 요청을 받지 않겠다는 cancel 메서드로 구성되어 있다.
    // publisher는 subscriber에게 data, complete, error 등 이벤트를 전달하고, Subscriber는 그 이벤트에 따라 on 으로 시작하는 메서드들을 통해서 이벤트들을 처리한다.



}