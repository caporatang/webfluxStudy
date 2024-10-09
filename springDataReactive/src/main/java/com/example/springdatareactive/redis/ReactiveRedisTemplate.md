# ReactiveRedisTemplate
ReactiveRedisTemplate은 Spring data redis reactive의 추상화 클래스로, ReactiveRedisConnectionFactory를 통해서 RedisConnection을 주입해서 사용한다.  

## ReactiveRedisConnectionFactory
ReactiveRedisConnectionFactory는 RedisConnection을 제공하며, LettuceConnectionFactory가 구현하고 있다.

RedisTemplate baen은 다음과 같은 구조로 되어있다.

````java
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;

@AutoConfiguration(after = RedisAutoConfiguration.class)
public class RedisReactiveAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = "reactiveRedisTemplate")
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory,
            ResourceLoader resourceLoader) {
        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(
                resourceLoader.getClassLoader());
        RedisSerializationContext<Object, Object> serializationContext = RedisSerializationContext
                .newSerializationContext()
                .key(jdkSerializer)
                .value(jdkSerializer)
                .hashKey(jdkSerializer)
                .hashValue(jdkSerializer)
                .build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory,
                serializationContext);
    }
    @Bean
    @ConditionalOnMissingBean(name = "reactiveStringRedisTemplate")
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        return new ReactiveStringRedisTemplate(reactiveRedisConnectionFactory);
    }

````
RedisReactiveAutoConfiguration을 통해서 자동으로 ReactiveRedisTemplate bean을 생성한다. 이때 JdkSerializationRedisSerializer는 ObjectOutputStream을 이용하여 key와 value로 주어지는 object를 binary로 변환한다. 추가적으로 key, value에 대해서 빈번하게 사용되는 String만 지원하는 ReactiveStringRedisTemplate bean도 함께 등록된다.  

### ReactiveRedisOperations
ReactiveRedisConnection에 직접 접근할 수 있는 execute, executeInSession 메서드를 제공하며, pub/sub 메서드, key 관련 메서드, 스크립트 메서드, operations 접근 메서드가 제공된다.
````java
public interface ReactiveRedisOperations<K, V> {
    <T> Flux<T> execute (ReactiveRedisCallback<T> action);
    <T> Flux<T> executeInSession (ReactiveRedisSessionCallback<K, V, T> action)
}

````

### pub/sub
- convertAndSend : destination 채널로 message를 전달하고 메세지를 받은 클라이언트의 숫자를 반환한다.
- listenToChannel : channels에 주어진 채널들을 listen하고 메세지를 Flux 형태로 전달한다.
````java
Mono<Long> convertAndSend(String destination, V message);
default Flux<? extends Message<String, V>> listenToChannel(String ... channels) { ... }
````

### key 관련
- hashKey : EXISTS. key 가 존재하는지 확은
- scan : SCAN. key들을 non-blocking으로 분할해서 순회
- delete : DELETE. key들을 삭제
- expire : EXPIRE. key에 TTL 부여
- expireAt : EXPIREAT. unix time을 기반으로 key에 TTL을 부여
- persist : PERSIST. key에 TTL을 제거
- getExpire : PTTL. key의 TTL을 milliseconds로 반환

````java
Mono<Boolean> hasKey(K key);
Flux<K> scan(ScanOptions options);
Mono<Long> delete(K... key);
Mono<Boolean> expire(K key, Duration timeout);
Mono<Boolean> expireAt(K key, Instant expireAt);
Mono<Boolean> persist(K key);
Mono<Duration> getExpire(K key);
````

### Operations
Lettuce에서 제공하는 자료구조형을 생각하면 된다.
- opsForValue: value
- opsForList: list
- opsForSet: set
- opsForHash: hash
- opsForZSet: sorted set
- opsForStream: stream
- opsForHyperLogLog: hyperLogLog
````java
<HK, HV> ReactiveHashOperations<K, HK, HV> opsForHash();
ReactiveHyperLogLogOperations<K, V> opsForHyperLogLog();
ReactiveListOperations<K, V> opsForList();
ReactiveSetOperations<K, V> opsForSet();
<HK, HV> ReactiveStreamOperations<K, HK, HV> opsForStream();
ReactiveValueOperations<K, V> opsForValue();
ReactiveZSetOperations<K, V> opsForZSet();
````

### ReactiveValueOperations
- set으로 특정 key에 value를 추가
- setIfAbsent로 key에 값이 없을때만 설정
- get으로 key의 value를 조회
- multiGet으로 여러 key에 접근
- increment로 특정 key의 value를 증가

````java
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueOperationExample {

    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        var commands = redisTemplate.opsForValue();
        commands.set("person:1:name", "capo")
                .then(commands.set("person:1:age", "20"))
                .then(commands.setIfAbsent("person:1:name", "notchanged"))
                .then(commands.get("person:1:name"))
                .doOnNext(name -> log.info("name: {}", name))
                .thenMany(commands.multiGet(List.of(
                        "person:1:name", "person:1:age")))
                .doOnNext(keyValue -> log.info("keyValue: {}", keyValue))
                .then(commands.increment("person:1:age", 10))
                .doOnNext(result -> log.info("result: {}", result))
                .subscribe();    
    }
}
````
````text
[lettuce-nioEventLoop-5-1] - name: capo
[lettuce-nioEventLoop-5-1] - keyValue: [capo, 20]
[lettuce-nioEventLoop-5-1] - result: 30
````

## ReactiveListOperations
- size: LLEN. list의 크기를 반환
- leftPush: LPUSH. list의 head에 값을 추가
- rightPush: RPUSH. list의 tail에 값을 추가
- set: LSET. 특정한 index에 값을 설정
- remove: LREM. list에서 value를 count 숫자만큼 제거
- leftPop: LPOP. list의 head에서 값을 제거하고 반환
- rightPop: RPOP. list의 tail에서 값을 제거하고 반환
- delete: DEL. key에 설정된 list를 제거

````java

var commands = redisTemplate.opsForList();

var stack1 = "queue:1";

commands.leftPush(stack1, "100")
    .then(commands.leftPush(stack1, "200"))
    .then(commands.size(stack1))
    .doOnNext(len -> log.info("queue:1 len: {}", len))
    .then(commands.rightPop(stack1))
    .doOnNext(pop -> log.info("pop: {}", pop))
    .then(commands.rightPop(stack1))
    .doOnNext(pop -> log.info("pop: {}", pop))
    .subscribe();
````
````text
[lettuce-nioEventLoop-5-1] - queue:1 len: 2
[lettuce-nioEventLoop-5-1] - pop: 100
[lettuce-nioEventLoop-5-1] - pop: 200
````
leftPush를 통해서 100, 200 값을 추가, size를 통해서 list의 크기를 출력, rightPop을 통해서 처음에 추가한 값들을 제거해서 queue를 구현한 예제이다.  


### ReactiveSetOperations
- add: SADD. value들을 set에 추가. 결과로 추가된 개수 반환
- remove: SREM: set에서 item들 제거
- size: SCARD. set의 cardinality 반환
- isMember: SISMEMBER. set에 item이 포함되는지 확인
- members: SMEMBERS. set의 모든 item 조회
- delete: DEL. key에 설정된 set을 제거

````java
var commands = redisTemplate.opsForSet();
var set1 = "set:1";
commands.add(set1, "100")
    .doOnNext(added -> log.info("added: {}", added))
    .then(commands.add(set1, "100", "200", "300"))
    .doOnNext(added -> log.info("added: {}", added))
    .then(commands.size(set1))
    .doOnNext(scard -> log.info("set card: {}", scard))
    .thenMany(commands.members(set1))
    .doOnNext(member -> log.info("member: {}", member))
    .then(commands.isMember(set1, "100"))
    .doOnNext(result -> log.info("result: {}", result))
    .then(commands.isMember(set1, "999"))
    .doOnNext(result -> log.info("result: {}", result))
    .then(commands.remove(set1, "100"))
    .subscribe();
````
````text
[lettuce-nioEventLoop-5-1] - added: 1
[lettuce-nioEventLoop-5-1] - added: 2
[lettuce-nioEventLoop-5-1] - set card: 3
[lettuce-nioEventLoop-5-1] - member: 100
[lettuce-nioEventLoop-5-1] - member: 200
[lettuce-nioEventLoop-5-1] - member: 300
[lettuce-nioEventLoop-5-1] - result: true
[lettuce-nioEventLoop-5-1] - result: false
````
add를 통해서 set에 값을 추가하고 size를 통해서 carainality를 출력하고 members로 모든 item을 조회한한다. isMember로 item이 set에 존재하는지 확인하고 remove로 set에서 item을 제거하는 예제이다.  


### ReactiveHashOperations
- remove: HDEL. hash에서 주어진 field key를 갖는 field들을 제거
- get: HGET. hash에서 주어진 field의 value 조회
- multiGet: HMGET. 여러 field의 value 조회
- increment: HINCRBY. 특정 field의 value를 주어진 값만큼 증가
- size: HLEN. hash의 field 크기 반환
- putAll: HSET. 여러 field들을 한번에 추가
- values: HGETALL. 모든 field들을 조회
- delete: DEL. key에 설정된 hash를 제거

````java
var commands = redisTemplate.opsForHash();
var hash = "person:1";
var fieldMap = Map.of("name", "taeil", "age", "20", "gender", "M");
commands.putAll(hash, fieldMap)
    .thenMany(commands.values(hash))
    .doOnNext(item -> log.info("item: {}", item))
    .then(commands.size(hash))
    .doOnNext(hlen -> log.info("hash len: {}", hlen))
    .then(commands.increment(hash, "age", 10))
    .thenMany(commands.multiGet(hash, List.of("name", "age")))
    .doOnNext(item -> log.info("item: {}", item))
    .then(commands.remove(hash, "age"))
    .subscribe();
````
````text
[lettuce-nioEventLoop-4-1] - item: KeyValue[gender, M]
[lettuce-nioEventLoop-4-1] - item: KeyValue[age, 20]
[lettuce-nioEventLoop-4-1] - item: KeyValue[name, taeil]
[lettuce-nioEventLoop-4-1] - hash len: 3
[lettuce-nioEventLoop-4-1] - item: KeyValue[name, taeil]
[lettuce-nioEventLoop-4-1] - item: KeyValue[age, 30]
````
putAll로 여러 field를 한번에 추가하고 values로 모든 필드에 접근해서 flux로 반환한다. size로 hash의 크기를 조회하고 increment로 필드 증가, multiGet으로 여러 필드에 대한 값을 조회하고 삭제하는 예제이다.  

### ReactiveZSetOperations
ZSet은 lettuce에서 봤던 SortedSet과 동일하다.
- addAll: ZADD. sorted set에 value와 score들을 추가
- remove: ZREM. set에서 value들을 제거
- rank: ZRANK. 주어진 value의 순위를 반환
- rangeWithScores: ZRANGE. 특정 범위 안의 value와 score를 조회
- size: ZCARD. sorted set의 cardinality 반환
- delete: DEL. key에 설정된 sorted set을 제거

````java
var commands = redisTemplate.opsForZSet();
var zset1 = "zset:1";

commands.addAll(zset1, List.of(
    TypedTuple.of("a", 10.0), TypedTuple.of("b", 1.0),
    TypedTuple.of("c", 0.1), TypedTuple.of("d", 100.0)
))
    .then(commands.remove(zset1, "d"))
    .doOnNext(result -> log.info("result: {}", result))
    .then(commands.size(zset1))
    .doOnNext(len -> log.info("zset len: {}", len))
    .thenMany(commands.rangeWithScores(zset1, Range.closed(0L, -1L)))
    .doOnNext(item -> log.info("zset item: {}", item))
    .then(commands.rank(zset1, "a"))
    .doOnNext(rank -> log.info("rank: {}", rank))
    .subscribe();
````
````text
[lettuce-nioEventLoop-5-1] - result: 1
[lettuce-nioEventLoop-5-1] - zset len: 3
[lettuce-nioEventLoop-5-1] - zset item: DefaultTypedTuple [score=0.1, value=c]
[lettuce-nioEventLoop-5-1] - zset item: DefaultTypedTuple [score=1.0, value=b]
[lettuce-nioEventLoop-5-1] - zset item: DefaultTypedTuple [score=10.0, value=a]
[lettuce-nioEventLoop-5-1] - rank: 2
````
addAll로 value를 한번에 여러개 추가하고, size로 크기 조회 및 rangeWithScores로 모든 value와 score 조회하고 rank로 특정 value의 순의를 출력하는 예제이다.


### ReactiveStreamOperations
- add: XADD. streams에 record를 추가
- createGroup: XGROUP. streams에 consumer group을 생성. group 이름 반환
- range: XRANGE. 주어진 범위의 record를 반환
- read: XREAD. 특정 offset 이후 혹은 최신 record를 count만큼 읽음. 최대 count개만큼 가져온 후 complete 이벤트 발생
- consumer 제공 가능

````java
var commands = redisTemplate.opsForStream();
var streamName = "stream:1";

var readArgs = StreamReadOptions.empty()
    .block(Duration.ofSeconds(10))
    .count(2);

var streamOffset = StreamOffset.latest(streamName);commands.read(readArgs, streamOffset)
.doOnSubscribe(subscription -> { 
    log.info("subscribed"); 
})
.subscribe(message -> {
    log.info("message: {}", message);
});
Thread.sleep(1000);
multiAdd(streamName);
````
````text
02:02 [main] - subscribed
02:03 [lettuce-nioEventLoop-5-2] - message:MapBackedRecord{recordId=1684803723808-0, kvMap={hello1=world1}}
02:03 [lettuce-nioEventLoop-5-2] - message:MapBackedRecord{recordId=1684803723808-1, kvMap={hello2=world2}}
````
10초동안 block되고 최대 2개를 받을 수 있는 option을 설정한 뒤, 10초가 지나면 next 이벤트 없이 complete 이벤트, lastest를 통해서 최신 record를 반환한다.  
read를 사용해야 하는 경우 read 전용 connection을 하나 따로 준비하는게 좋다..!