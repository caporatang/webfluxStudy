package com.example.webfluxstudy.basicReactiveProgramming.reactive.reactiveStreams.hotPublisherColdPublisher;

/**
 * packageName : com.example.webfluxstudy.reactive.reactiveStreams.hotPublisherColdPublisher
 * fileName : HotColdPublisher
 * author : taeil
 * date : 2023/10/18
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/18        taeil                   최초생성
 */
public class HotColdPublisher {
    // Hot Publisher : 외부에 의해서 변동되는 경우
    // subscriber가 없더라도 데이터를 생성하고 stream에 push하는 publisher
    // 트위터 게시글 읽기, 공유 리소스 변화 등....
    // 여러 subscriber에게 동일한 데이터 전달 가능

    // Cold Publisher: reactiveStremasExample/FixedIntPublisher
    // subscribe가 시작되는 순간부터 데이터를 생성하고 전송
    // 파일 읽기, 웹 API 요청 등
    // subscriber에 따라 독립적인 데이터 스트림 제공
}