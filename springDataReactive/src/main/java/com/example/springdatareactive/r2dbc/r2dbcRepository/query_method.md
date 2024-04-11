## Query method
- R2dbcRepository를 상속한 repository interface에 메소드를 추가
- 메소드의 이름을 기반으로 Query 생성 조회 삭제 지원
- @Query 어노테이션을 사용해서 복잡한 쿼리나 update 문도 실행 가능
```` java
public interface PersonQueryMethodRepository extends ReactiveSortingRepository<PersonEntity, Long> {

    Flux<PersonEntity> findAllByName(Publisher<String> name);
    
    Mono<PersonNameOnly> findFirstByName(String name);
    
    Mono<PersonEntity> findFirstByNameOrderByAgeDesc(String name);
    
    Mono<Integer> deleteByName(String name);
    
    Mono<Boolean> deleteByAgeGreaterThan(int age);
    
    Mono<Void> deleteAllByGender(Publisher<String> gender);
    
    @Query("select * from person where name = :name group by name")
    Mono<PersonEntity> findFirstGroupByName(String name);
    
    @Query("select p.* " +
    "from person p inner join person_role pr " +
    "on p.id = pr.person_id " +
    "where pr.role = :role")
    Flux<PersonEntity> findAllByRole(String role);
    
    @Modifying
    @Query("update person set name = :name where id = :id")
    Mono<Integer> updateNameById(String name, Long id);
}
````

## Query method - find
- id 뿐만 아니라 다른 필드를 이용해서 조회 가능
- first 등의 키워드를 사용해서 query에 limit 제공 가능 
- 기존의 Entity 뿐만 아니라 Projection을 사용하여 일부 필드만 조회 가능
~~~java
public interface PersonQueryMethodFindRepository extends ReactiveSortingRepository<PersonEntity, Long> {
    Flux<PersonEntity> findAllByName(Publisher<String> name);
    Mono<PersonNameOnly> findFirstByName(String name);
    Mono<PersonEntity> findFirstByNameOrderByAgeDesc(String name);
}
~~~
- findFirstByNameOrderByAgeDesc
- name이 "taeil"인 row들을 찾고 age 내림차순으로 Sort, limit 1 
- 모든 field를 조회하여 PersonEntity class로 mapping
- Projection을 사용하고 싶으면 반환값을 Sub class로 지정하면 자동으로 projection이 적용된다
> - R2dbcRepository에서는 조회를 모든 결과를 다 조회해서 반환값에 매핑 시켜준다면, Query method는 모두 조회 하는게 아니라 원하는 값만 조회한다   
> -> 성능은 당연히 좋아지겠지
~~~java
    @SpringBootApplication
public class R2dbcQueryMethodFindExample
        implements CommandLineRunner {
    @Autowired
    private PersonQueryMethodRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        personRepository.findFirstByNameOrderByAgeDesc("taeil")
                .subscribe(person -> {
                    log.info("person: {}", person);
                });
        System.exit(0);
    }
}
~~~

## Query method - delete
- 다른 필드를 이용해서 삭제 가능
- 여러 반환 타입 지원
> - Integer : 영향을 받은 row 수 반환
> - Boolean : 삭제되었는지 여부 반환
> - Void : 반환값보다는 completion이 중요한 경우
```` java
public interface PersonQueryMethodDeleteRepository extends ReactiveSortingRepository<PersonEntity, Long> {
    Mono<Integer> deleteByName(String name);
    Mono<Boolean> deleteByAgeGreaterThan(int age);
    Mono<Void> deleteAllByGender(Publisher<String> gender);
}  
````
- deleteByAgeGreaterThan
- age가 100 초과인 row를 찾고 삭제한 후 영향을 받은 row가 있다면 true, 없다면 false를 반환
~~~ java
@SpringBootApplication
public class R2dbcQueryMethodDeleteBooleanExample implements CommandLineRunner {
    @Autowired
    private PersonQueryMethodDeleteRepository personRepository;
    
    @Override
    public void run(String... args) throws Exception {
    personRepository.deleteByAgeGreaterThan(100)
    .subscribe(person -> {
        log.info("person: {}", person);
    });
        System.exit(0);
    }
}
~~~ 

### Query method 시작 키워드
- find, read, get, query, search, stream : find 쿼리를 실행하고 결과를 Publisher<T>로 반환
- exists : find exists 쿼리를 실행하고 결과를 Publisher<Boolean> 로 반환
- count : find count 쿼리를 실행하고 결과를 Publisher<Integer> 로 반환
- delete, remove : delete 쿼리를 실행하고 Publisher<Void> 혹은 publisher<Integer> 로 삭제된 개수 반환

### Query method 제한 키워드
- First<N>, Top<N> : 쿼리의 limit을 N으로 설정. find와 By 사이 어디에서든 사용 가능
- Distinct : distinct 기능을 제공. find와 By 사이 어디에든 등장 가능

### 수많은 predicate 키워드
**And: AND**  
**Or: OR**  
**After, IsAfter: AFTER**    
**Before, IsBefore: BEFORE**    
Containing, IsContaining, Contains  
- **CONTAINING**  
Between, IsBetween: BETWEEN    
• EndingWith, IsEndingWith, EndsWith    
- **ENDING_WITH**    
• Exists: EXISTS  
• False, IsFalse: FALSE  
• GreaterThan, IsGreaterThan: GREATER_THAN  
• GreaterThanEqual, IsGreaterThanEqual  
- **GREATER_THAN_EQUALS**  
• In, IsIn: IN  
• Is, Equals: IS  
• IsEmpty, Empty: IS_EMPTY  
• IsNotEmpty, NotEmpty: IS_NOT_EMPTY  
• NotNull, IsNotNull: IS_NOT_NULL  
• Null, IsNull: IS_NULL  
• LessThan, IsLessThan: LESS_THAN  
• LessThanEqual, IsLessThanEqual   
- **LESS_THAN_EQUAL**  
• Like, IsLike: LIKE    
• Near, IsNear: NEAR    
• Not, IsNot: NOT  
• NotIn, IsNotIn: NOT_IN  
• NotLike, IsNotLike: NOT_LIKE  
• Regex, MatchesRegex, Matches: REGEX  
• StartingWith, IsStartingWith, StartsWith  
- **STARTING_WITH**  
  • True, IsTrue: TRUE  
  • Within, IsWithin: WITHIN  
  • IgnoreCase, IgnoringCase: 특정 필드에 적용. 비교 하려는 대상 모두 UPPER로 만들어서 비교  
  • AllIgnoreCase, AllIgnoringCase  
  • OrderBy: 주어진 property path와 direction에 따라서 쿼리에 Sort 제공  

### Query method - 반환 타입 (1)
> - Mono
> 1. Reactor에서 제공
> 2.  0개 혹은 하나의 값을 반환하는 Publisher
> 3.  만약 결과가 2개 이상이라면 IncorrectResultSizeDataAccessException 발생

> - Flux
> 1. Reactor에서 제공
> 2. 0개 이상의 값을 반환하는 Publisher
> 3. 끝이 없는 수의 결과를 반환 가능

>- Single
> 1. RxJava에서 제공
> 2. 무조건 1개의 값을 반환하는 Publisher
> 3. 만약 결과가 2개 이상이라면 IncorrectResultSizeDataAccessException 발생
> 4. 만약 결과가 0개라면 NoSuchElementException 발생

> - Maybe 
> 1. RxJava에서 제공
> 2. 0개 혹은 하나의 값을 반환하는 publisher
> 3. 만약 결과가 2개 이상이라면 IncorrectResultSizeDataAccessException 발생

> - Flowable
> 1. RxJava에서 제공
> 2. 0개 이상의 값을 반환하는 Publisher
> 3. 끝이 없는 수의 결과를 반환 가능

### Query method - @Query
- query가 메소드 이름으로 전부 표현이 되지 않는 경우
- 쿼리 메소드 예약어에서 지원되지 않는 문법을 사용하는 경우
- 복잡한 query문을 사용하는 경우
~~~java
public interface PersonQueryMethodAnnotationRepository extends ReactiveSortingRepository<PersonEntity, Long> {
    
    @Query("select * from person where name = :name group by name")
    Mono<PersonEntity> findFirstGroupByName(String name);
    
    @Query("select p.* " + "from person p inner join person_role pr " + "on p.id = pr.person_id " + "where pr.role = :role")
    Flux<PersonEntity> findAllByRole(String role);
    
    @Modifying
    @Query("update person set name = :name where id = :id")
    Mono<Integer> updateNameById(String name, Long id);
}
~~~
- Join을 사용하는 경우
- inner join을 이용하여 person_role과 Join 하여 role이 특정값인 경우에만 조회, 결과를 PersonEntity 형태로 반환
~~~java
  @SpringBootApplication
public class R2dbcQueryMethodAnnotationJoinExample implements CommandLineRunner {
    @Autowired
    private PersonQueryMethodAnnotationRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        personRepository.findAllByRole("ADMIN")
                .subscribe(person -> {
                        log.info("person: {}", person);
                });
        System.exit(0);
    }
}
~~~

## @Transactional (1)
- @Transactional을 사용하여 여러 query를 묶어서 진행
- 새로운 Entity를 만들어서 save하고 update한 후 findAll을 통해서 모든 row 반환
~~~java
    @Service
public class PersonService {
    private final PersonRepository personRepository;
    
    @Transactional
    public Flux<PersonEntity> savePerson() {
        var person = new PersonEntity(null, "peter", 50, "M");
        return personRepository.save(person)
                .flatMap(savedPerson -> {
                        var personToUpdate = savedPerson.withAge(30);
            return personRepository.save(personToUpdate);
        }).thenMany(personRepository.findAll());
    }
}
~~~

## @Transactional 을 사용하기 힘든 경우 사용할 수 있는 TransactionalOperator
- 이런 경우 : private 메서드에 대해서 @Transactional을 걸어야 하거나,  내부에서 내부를 호출하는 경우 ..
- transactional 메소드를 통해서 주어진 Flux 혹은 Mono를 transaction 안에서 실행
~~~java
public interface TransactionalOperator {
    default <T> Flux<T> transactional(Flux<T> flux) {
        return execute(it -> flux);
    }
        <T> Mono<T> transactional(Mono<T> mono);

        <T> Flux<T> execute(TransactionCallback<T> action) throws TransactionException;
    }
~~~

### TransactionalOperator 사용 예제
- flux를 바로 반환하지 않고 transactionalOperator의 transactional로 wrapping 하여 전달
- 혹은 execute를 통해서 TransactionCallback 형태로 실행
~~~java
public class example {
    @Transactional
    public Flux<PersonEntity> savePerson2() {
        var person = new PersonEntity(null, "peter", 50, "M");
        var jobs = personRepository.save(person)
        .flatMap(savedPerson -> {
            var personToUpdate = savedPerson.withAge(30);
            return personRepository.save(personToUpdate);
        }).thenMany(personRepository.findAll());
            return transactionalOperator.transactional(jobs); // 통째로 넘기기
        }

    public Flux<PersonEntity> savePerson3() {
        var person = new PersonEntity(null, "peter", 50, "M");
        var jobs = personRepository.save(person)
                .flatMap(savedPerson -> {
                        var personToUpdate = savedPerson.withAge(30);
        return personRepository.save(personToUpdate);
        }).thenMany(personRepository.findAll());
            // callback 형태로 넘김
            return transactionalOperator.execute(status -> jobs);
        }
}
~~~