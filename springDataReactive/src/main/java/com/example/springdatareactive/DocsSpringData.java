package com.example.springdatareactive;

/**
 * packageName : com.example.springdatareactive
 * fileName : DocsSpringData
 * author : taeil
 * date : 2/27/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/27/24        taeil                   최초생성
 */
public class DocsSpringData {
    // Spring Data?
    // 데이터 저장소의 특성을 유지하면서 Spring 기반의 프로그래밍 모델을 제공한다.
    // 다양한 데이터 접근 기술을 지원한다.
    // - 관계형 DB, 비관계형 DB
    // - map-reduce 프레임워크
    // - 클라우드 기반의 data 서비스

    // Spring data reactive를 지원한다.
    // 1. R2DBC -> data reactive 에 특화된 데이터 베이스
    // 2. MongoDB
    // 3. Redis
    // 4. Apache Cassandra
    // 5. Apache Solr
    // 6. Couchbase
    // 7. Elasticsearch
    // 8. Neo4j

    // Spring data reactive를 지원한다는건 어떤 의미를 가지고 있을까?
    // Reactive streams, Reactor, Netty client, Java NIO, Selector를 사용하여 비동기 non-blocking을 지원한다.
    // Reactive client를 제공하고 이를 기반으로 ReactiveTemplate 혹은 ReactiveRepository를 구현한다.
    // 데이터베이스에 대한 작업의 결과로 대부분 Publisher를 반환한다.
}