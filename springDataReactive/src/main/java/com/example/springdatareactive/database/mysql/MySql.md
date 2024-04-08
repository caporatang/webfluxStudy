## MYSQL
#### mysql의 역사
> * 1995년 스웨덴의 MySQL AB라는 회사에 의해 첫 번째 버전 발표
> * 2008년 Sun Microsystems가 MySQL AB를 인수하고 5.1 버전 발표
> * 2010년 Oracle이 Sun을 인수하여 현재까지 Oracle이 MySQL을 관리
> * LAMP(Linux, Apache, MySQL, PHP) 스택에 힘입어 대표적인 관계형 데이터베이스로 자리잡음

### 특징
> * 오픈 소스 기반의 관계형 데이터베이스 
> * 데이터 읽기 쓰기 등에서 높은 퍼포먼스 제공
> * 인덱스, 여러 튜닝 옵션 등을 통해서 성능을 높일 수 있다.
> * 트랜잭션 ACID(원자성, 일관성, 고립성, 지속성) 준수
> * 마스터-슬레이브 구조를 통한 데이터 복제, Replication 지원
> * InnoDB, MyISAM, Memory, Archive 등 다양한 스토리지 엔진 지원

### MySQL 엔진
> * InnoDB, MyISAM, Memory, Archive, CSV 등 이와 같은 엔진이 존재하는 이유는
> * 데이터 처리 및 성능에 따른 각각 다른 요구사항을 충족시키기 위해서.
> * 엔진의 특성에 따라서 인덱스나 트랜잭션이 지원되지 않을 수 있으며, 읽기 쓰기 성능에 차이가 생긴다.  
> ### InnoDB 엔진
> > * MySQL에서 가장 많이 사용되며, 5.5 버전 이후 기본 엔진이다.
> > * 트랜잭션 처리를 위해 설계되었고, 데이터의 일관성과 무결성이 보장되고, ACID 원칙을 준수한다.
> > * MVCC (Multi Version Concurrency Control) 기술을 이용해서 트랜잭션 간 충돌을 방지하고 읽기 성능을 향상 시켰다.
> > * 자동으로 데드락을 감지하여 강제 종료하며 완료되지 못한 트랜잭션에 대한 복구 작업도 자동으로 수행한다
> > * 외래 키 지원
> > * 대규모 트랜잭션 지원이 필요한 서비스에 적합하다.
> ### MyISAM 엔진
> > * 5.5 버전 이전 기본 엔진
> > * 전문 검색과 텍스트 검색을 지원한다.
> > * 풀 스캔에 대한 성능을 최적화하여 전체 테이블 검색할 때 유용하다.
> > * 비트맵 인덱스를 지원하여 대규모 데이터 성능을 향상
> > * 여러 사용자가 하나의 테이블에 작업을 수행할 때, 테이블 단위로 잠금, 단 트랜잭션과 row 단위의 잠금은 지원하지 않는다.
> > * 트랜잭션을 지원하지 않기 때문에 심플하고 빠르지만 동시성 제어가 어렵다.
> > * 검색 기능이 많이 사용되고 읽기 쿼리가 많은 서비스에 적합하다.
> ### Memory 엔진
> > * 데이터를 메모리에 저장하여 빠르게 검색 가능
> > * MySQL 서버를 재시작하면 저장된 데이터가 모두 손실 -> 일시적인 데이터를 저장하는데 유용하다
> > * 인덱스를 파일 시스템이 아닌 특수한 메모리 구조로 유지한다.
> > * 여러 사용자가 하나의 테이블에 작업을 수행할 때, 테이블 단위로 잠금
> > * 빠른 데이터 검색이 필요한 서비스 작업에 적합하다. 

### 트랜잭션
* 하나 이상의 SQL 작업을 단일 작업 단위로 실행하는 일련의 과정
* 데이터의 무결성과 일관성 보장
> 맨날 까먹는 ACID 
> > Atomicity (원자성) : 트랜잭션의 모든 작업이 완료되거나 아무것도 수행되지 않아야 한다.
> > Consistency (일관성) : 트랜잭션 이후 데이터베이스가 일관된 상태를 유지한다.
> > Isolation (고립성) : 동시에 실행되는 트랜잭션 사이에 서로 영향을 주지 않아야 한다.
> > Durability (지속성) : 완료된 트랜잭션의 변경 사항이 영구적으로 반영
