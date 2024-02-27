## mongoDB
#### MonoDB의 역사
> * 2007년 10gen 라는 회사에서 Paas로 MongoDB 개발 시작
> * 2009년 오픈 소스 모델로 전환하여 상용 지원과 기타 서비스를 제공하는 방식 채택
> * 2013년 10gen 사명을 MongoDB Inc.로 변경
> * 가장 유명한 NoSQL 데이터베이스.

### 특징
* 오픈소스 기반의 NoSQL 데이터베이스
* 사전 정의된 스키마 없이 유연하게 데이터 저장
>* 데이터를 여러 노드에 분산하여 저장, 처리 가능  
>  - mysql은 분산저장을 지원하지 않나?  
>  방식이 다르다.  
>  mysql은 Replication 즉, 데이터를 복제해서 들고있는 형태를 갖고, 복제된 데이터를 read 하는 상황에서 복제된 데이터를 조회할 수 있게 해서 성능을 높임
>  mongoDB는 복제를 하는게 아니라 태생부터데이터를 여러 노드에 저장한다.  
>  mysql 에서도 sharding 을 설정하면 분산 저장은 가능하지만 DB자체에서 지원하는게 아니라 개발자가 어플리케이션에서 따로 설정을 해줘야한다.
* 다양한 종류의 인덱스 지원 
* 4.0 부터 multi document transaction을 통해서 트랜잭션 ACID 준수

### BSON (Binary Json)
* mongoDB는 BSON 형태로 데이터를 저장한다.
* Binary 형식의 JSON 이다.
* JSON 보다 빠른 인코딩/디코딩
* 필드의 순서에 따라서 인코딩 결과가 달라진다.
> * 다양한 타입을 지원한다  
> document의 id : ObjectId  
> 바이너리 데이터 : BinData  
> 날자 : Date, SIODate  
> 정규표현식
* mmongoDB 문서들이 저장되는 포맷이다.
```bson
{
"_id": ObjectId("617f067b5a63b30c1db7910f"),
"name": "John Doe",
"age": 30,
"email": "johndoe@example.com",
"address": {
"city": "New York",
"zipcode": "10001",
"street": "123 Main St"
},
"interests": ["hiking", "reading", "coding"]
}
```
### BSON의 인코딩
\x1B\x00\x00\x00 <!-- _id 필드 -->
\x04_id\x00\xE5\xF8%\x17P\xA0#\xE3E\x06\xB9\xD5\x00 <!-- ObjectId 값 -->
\x05name\x00\x08\x00\x00\x00John Doe\x00 <!-- name 필드 -->
\x06age\x00\x1E\x00\x00\x00\x00 <!-- age 필드 -->
\x07email\x00\x13\x00\x00\x00johndoe@example.com\x00 <!-- email 필드 -->
\x08address\x00\x1C\x00\x00\x00 <!-- address 필드 -->
\x03city\x00\n\x00\x00\x00New York\x00 <!-- city 값 -->
\x03zip\x00\x05\x00\x00\x0010001\x00 <!-- zip 값 -->
\x00\x00\x08is_active\x00\x01 <!-- is_active 필드 -->
\x09tags\x00+\x00\x00\x00 <!-- tags 필드 -->
\x020\x00\x08\x00\x00\x00mongodb\x00 <!-- tags 배열 요소 -->
\x08\x00\x00\x00database\x00 <!-- tags 배열 요소 -->
\x08\x00\x00\x00NoSQL\x00 <!-- tags 배열 요소 -->
\x00 <!-- BSON 데이터 종료 -->
* 첫 줄은 전체 Document의 크기를 가리킨다.
* 데이터 타입, 필드명, 길이(데이터 타입에 따라 옵셔널) 값으로 구성된다.

### MongoDB Document
- 하나의 개체를 가리킨다.
- RDB의 Row에 해당한다.
- 필드와 값으로 구성되며, 중첩된 하위 Document나 배열을 포함할 수 있다.
- BSON 형식으로 저장되며, BSON에서 지원하는 데이터 타입을 사용해야 한다.

### MongoDB Collection
- MongoDB Document들을 저장하는 단위이다 
- RDB에서의 테이블 개념 -> Person이라는 Document를 모두 저장한다면 Person Collection이 생긴다
- 하나의 Collection 내에서 Document마다 다른 필드와 데이터 타입을 가질 수 있다.

### MongoDB Database
- MongoDB Collection을 저장하는 컨테이너다.
- Document를 추가할 Collection이 없다면 자동으로 collection이 생성된다.


### MongoDB 트랜잭션
- 4.0 버전 이전에는 트랜잭션이 지원되지 않았지만 4.0 이후부터 트랜잭션을 지원한다.
- 여러 개의 문서와 컬렉션 사이에 작업을 수행할 수 있다.
- ACID를 보장한다.
- 여러 샤드에 영향을 주는 트랜잭션의 경우, 쿼리가 브로드캐스트 되므로 성능에 크게 영향을 줄 수 있다.
- 1번 재시도 후 실패시 클라이언트에 에러를 전파한다.