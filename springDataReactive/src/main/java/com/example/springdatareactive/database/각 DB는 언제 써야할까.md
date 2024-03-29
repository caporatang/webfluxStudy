## 각 DB 사용 사례

#### MySQL
1. 정형화된 데이터 구조가 필요한 서비스
>table, row, column, association 등을 통해서 데이터 표현  
> 예) 인사관리 서비스, 고객 관리 서비스
2. 다양한 분석과 집계가 필요한 서비스 
> 복잡한 쿼리와 join 처리 등을 제공
> 예) 데이터 웨어하우스, 보고서 대시보드 시스템..등..
3. 데이터 일관성, 안정성이 중요한 서비스
> 트랜잭션으로 데이터 일관성 제공  
> master-slave 기반의 리플리케이션으로 고가용성과 안정성을 제공
> 예) 금융 서비스, 이커머스 서비스 등...


#### MongoDB
1. 유연한 데이터 구조가 필요한 서비스
> JSON 기반의 document로 유연하게 스키마를 표현
> 예) 컨텐츠 관리 시스템 등
2. 빠른 개발이 필요한 프로젝트
> 예) 프로토타입 개발, 스타트업 프로젝트
3. 데이터 쓰기가 많은 서비스
> BSON을 통해 쓰기가 빠르고 리소스를 효율적으로 사용한다.
> collection을 여러 서버로 분할하여 저장 및 조회하는 sharding 지원
> 예) IoT 및 센서 데이터 관리 서비스 등..
4. 대용량 데이터를 처리하는 서비스
> 데이터를 여러 서버에 나눠서 저장하고 쉽게 서버 추가 가능
> 예) 로그 분석 및 모니터링

#### Redis
1. 메인 데이터베이스 보다는 부가적으로 사용되는 경우가 대부분이다.
2. 다양한 자료구조가 필요한 서비스
> String 뿐만 아니라 List, Set, Hash, Sorted set 등의 고급 데이터 구조 등..도 지원한다
> 예) 허용 Ip 관리 서비스(Set), 랭킹 시스템(Sorted set), DAU 추정 서비스(HyperLogLog)
3. 높은 성능과 데이터 일관성이 필요한 서비스
> 싱글 쓰레드와 eventLoop를 활용하여 높은 성능을 보장한다.
> task queue에서 task를 수행하기 때문에 데이터 일관성을 제공한다.
> 예) 재고 관리 서비스, 요청 제한 서비스 등...
4. 글로벌 캐시가 필요한 서비스
> String, Hash 등의 자료구조를 통해서 데이터, 객체 등을 저장하기 용이하다.
> 예) 유저 관리 서비스, 이미지 URL 관리 서비스 등.
