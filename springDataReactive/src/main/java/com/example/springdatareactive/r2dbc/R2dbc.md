## R2DBC

- 왜 JDBC, JPA는 non-blocking을 지원할 수 없을까?
1. JDBC는 동기 blocking I/O 기반으로 설계되어 있다.
2. Socket에 대한 연결과 쿼리 실행 모두 동기 blocking으로 동작한다
3. 이미 널리 사용되고 있기 때문에 JDBC를 수정하는것은 사실상 불가능하다.
4. JPA 또한 jdbc 기반이기 때문에 비동기 Non-blocking도 지원 불가능하다
-> 비동기 Non-blocking 기반의 API, 드라이버를 새로 만들자 ! 가 된것임.  

#### R2DBC 란?
- Reactive Relational Database Connectivity  
- 2017년 Pivotal사에서 개발이 시작되어 2018년부터 공식 프로젝트로 후원되기 시작  
- 비동기, Non-blocking 관계형 데이터베이스 드라이버  
- Reactive streams 스펙을 제공하며 Project reactor 기반으로 구현되어 있다.

#### R2dbc 지원 데이터베이스
- 공식 지원
> r2dbc-h2 : 테스트 데이터베이스로 사용되는 h2
> r2dbc-mssql : Microsoft SQL Server
> r2dbc-pool : Reactor pool로 커넥션 풀 제공
- 벤더 지원
> oracle-r2dbc : oracle 사에서 oracle db 지원
> r2dbc-mariadb : maria 재단에서 지원
> r2dbc-postgresql : postgre 개발 그룹에서 지원
- 커뮤니티 지원
> r2dbc-mysql : MySQL 지원
> github.com/mirromutth/r2dbc-mysql 에서 제공하다가 2020년부터 업데이트 되고 있지 않으니 사용하면 안된다.
> github.com/asyncer-io/r2dbc-mysql 로 포크 진행해서 r2dbc-spi 1.0.0. RELEASE를 지원하고 이후 버전부터 사용할것.

#### R2dbc MySQL 구조
- r2dbc-spi 와 Reactor Netty 기반
- Reactor Netty를 이용하여 r2dbc-spi 스펙을 구현.
- Reactor Netty client로 성능과 확장성을 모두 제공한다.
- r2dbc-spi 스펙을 구현하여 여러 데이터베이스 시스템과 호환.

#### R2dbc SPI
- R2dbc Service Provider Interface : 각각의 스펙에 맞는 인터페이스를 구현해서 사용해야한다.
- Connection, ConnectionFactory 등 db connection 스펙
- R2dbcException, R2dbcTimeoutException, R2dbcBadGrammarException 등의 excpetion 스펙
- Result, Row, RowMetadata 등 result 스펙
- Statement 등 statement 스펙  
-> JDBC와 비슷한 형태를 띄고 있음. 

#### R2dbc SPI Connection 
- 데이터베이스에 대한 연결을 가리킨다.
- Closable을 구현하여 close 메서도르 connection을 닫을 수 있다.
- ConnectionMetadata를 제공한다.
> database 의 version과 productName을 제공한다.
- createStatement를 통해서 sql을 넘기고 Statement를 생성한다.
- transaction 관련된 기능을 제공
- transaction을 시작
> transactionDefinition 로 고립 수준, 읽기 전용 여부, 이름, lockWaitTime 등을 설정
> transaction savepoint를 만들고 rollback 가능
> transaction 을 commit 하거나 rollback

#### R2dbc SPI ConnectionFacotry
- connection을 생성하는 factory 
- ConnectionFactoryMetadata를 통해서 ConnectionFactory의 정보를 제공
- ConenctionFactoryMetadata는 name을 통해서 제공

#### R2dbc SPI Statement
- Statement는 PreparedStatement 처럼 여러가지 바인딩을 만들고, 바인딩을 이용해서 쿼리를 실행하는 계층
- Statement는 Connection 으로부터 createStatement를 통해서 생성한다.
- bind : sql에 parameter를 Bind
> index, name 단위로 Parameter를 bind
> add : 이전까지 진행한 Binding을 저장하고 새로운 binding을 생성한다.
> execute : 생성된 binding 수만큼 쿼리를 실행하고 Publisher로 반환