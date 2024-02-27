## Redis
#### redis의 역사
- 2009년 이탈리아의 개발자가 개발한 오픈 소스 인 메모리 Key-value 구조의 데이터베이스
- 2015년 현재까지 Redis Labs에서 지원
- 가장 많이 쓰이는 Key-Value 구조의 저장소

### 특징
- 오픈 소스 기반의 인 메모리 Key-Value 데이터 베이스
- 메모리에 저장하여 빠른 응답 속도를 제공한다.
- 싱글 쓰레드로 구현되어 요청을 빠르게 처리하고 데이터 정합성을 보장한다.
- String, Hash, List, Set, Sorted set, Stream, HyperLogLog 등 다양한 데이터타입을 제공한다.
- 마스터-슬레이브 구조를 통한 데이터 복제, Replicaiton을 지원
- >RDB(Redis Database)와 AOF(Append Only File) 방식으로 영속성 부여 가능  
  > RDB: Redis 데이터의 스냅샷을 일정 시간 간격으로 디스크에 저장한다.  
  > AOF :모든 데이터 변경 작업을 로그로 기록한다.

### Redis의 구조
- >단일 쓰레드로 동작, I/O multiplexing을 이용 -> netty랑 비슷한 구조  
  > 모든 작업들이 Task queue를 통해서 순차적으로 처리되므로 데이터 정합성, 일관성 등을 보장
- 이벤트 드리븐 모델을 사용한다.
- TCP 프로토콜을 지원하며, RESP(Redis Serialization Protocol) 를 사용한다.
- Redis Database와 Append Only File 등으로 영속성 지원

### Redis Key-Value
- Redis는 인메모리 기반으로 고성능 Key-Value 저장소를 제공
- 데이터베이스, 캐시, 메세지 브로커로 사용 가능
- Key-Value 쌍으로 데이터를 저장한다 -> Key는 유니크해야하며 최대 512MB까지 지원한다.

### Redis Value
- String : 텍스트, 숫자, 이진 데이터 저장
- List : 순서가 있는 문자열 리스트
- Set : 순서가 없는 유일한 문자열 세트
- Hash : Key-Value 쌍의 모음
- Sorted set : Set과 비슷하지만 각각에 score를 부여하여 sort
- Stream : 실시간으로 이벤트를 기록하고 생성된 이벤트를 구독할 수 있는 스트림
- HyperLogLog : 확률적인 데이터 구조. 고유한 요소의 개수를 추정하는데 사용하며 높은 메모리 효율
> 여러개의 클라이언트에서 동시에 접근할 수 있는 shared 자료구조라고 생각하면 된다.

### Redis String
- 문자열, 직렬화된 객체, 이진 배열 등 기본적인 값들을 저장한다.
- 캐싱에 자주 사용되며 카운터를 구현하고 비트 연산 등도 지원한다
> SET : 특정 key에 string 값을 저장한다  
 SETNX : 특정 key에 값이 없는 경우에만 값을 저장한다.  
 GET : 특정 Key에 저장된 값을 조회한다.  
 MGET : 하나의 요청으로 여러 key를 조회한다.  
 INCRBY : 특정 key에 저장된 값을 주어진 수만큼 증가 -> 음수가 주어지면 감소한다.  

### Redis List
- 문자열로 구성된 linked list 구조이다. 
- linked list 이기 때문에 글로벌하게 사용할 stack과 queue가 필요한 경우 사용하는게 좋다.
- 최대 길이는 2^32-1개 (4,294,967,295)
> LPUSH : list의 head에 값을 추가  
> RPUSH : list의 tail에 값을 추가  
> LPOP : list의 head에서 값을 제거하고 반환  
> RPOP : list의 tail에서 값을 제거하고 반환  
> LLEN : list의 길이를 반환  


### Redis Set
- 문자열로 구성된 순서 없는 collection
- 유니크한 항목들을 추적해야하는 경우
- 관계를 표현하는 경우
- 최대 길이는 2^32-1개 (4,294,967,295)
> SADD : set에 값을 추가   
> SREM : set에서 값을 제거  
> SISMEMBER : set에 값이 포함되어있는지 확인  
> SCARD : set의 크기를 반환  
> SMEMBERS : set의 모든 value 목록을 반환  
> 추가, 삭제, 확인 등 대부분의 작업의 복잡도가 O(1), 하지만 SMEMBERS는 O(n) 이다. 

### Redis Hash
- field-value 쌍의 Collection -> java Map 과 동일
- 기본적인 객체를 표현해야 하는 경우
- counter의 모음을 저장해야 하는 경우
- 최대 길이는 2^32-1개 (4,294,967,295)
> HSET : hash에 값을 추가, field value 순으로.  
> HGETALL : hash의 모든 값을 field value 순으로 출력    
> HLEN : hash의 field-value 쌍 개수를 반환  
> HINCRBY : hash의 특정 field의 value를 증가  
> HGET : 주어진 field 들의 value들을 조회  
> HDEL : 주어진 field의 값을 제거  







