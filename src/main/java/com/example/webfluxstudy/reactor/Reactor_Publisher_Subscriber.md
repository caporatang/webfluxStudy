## Reactor
Reactive streams를 구현한 비동기 데이터 스트림 처리를 지원한다.  
Spring webflux에서 메인으로 사용되며, backpressure를 제공하여 안정성을 높이고 다양한 연산자로 다양한 연산들을 조합하여 가독성을 증대시킨다.  

데이터는 소스(Publisher)에서 나와서 소비자(Subscriber)에게 전달된다. 데이터는 다양한 변형과 중간 단계를 거치게되고 중간 부품을 모으는 더 큰 조립 라인의 일부가 된다.  
한 지점에서 결함이 발생하거나 처리가 불가능해진다면 업스트림에 신호를 보내서 흐름 제한도 가능하다.  

## Reactor Publisher
Reactor에서 Mono와 Flux를 제공한다. CorePublisher는 reactive streams의 Publisher를 구현하며, reactive streams와 호환된다.
![Reactor](img/Reactor.png)  

### Flux
0..n개의 item을 subscriber에게 전달한다.  
subscriber에게 onComplete, onErrorsignal을 전달하면 연결을 종료한다.
모든 event가 optional하기 때문에 다양한 flux를 정의해서 사용 가능하며, onComplete도 optional하게 사용할 수 있다.  
onComplete를 호출하지 않으면 sequence도 무한으로 생성할 수 있다.


### Mono
0..1개의 item을 subscriber에게 전달한다.  
subscriber에게 onComplete, onErrorsignal을 전달하면 연결을 종료한다.  
모든 event가 optional하게 사용 가능하며, onNext가 호출되면 곧바로 onComplete 이벤트를 전달한다.  

### Mono는 왜 쓰는가
Flux에 값을 1개만 세팅해서 반환하거나 사용하면 Mono랑 똑같은거 아닌가?  
Mono는 **반드시 하나의 값 혹은 객체를 필요로 하는 경우**에 사용한다. 예를들어 유저가 작성한 게시글의 숫자, http응답 객체처럼 꼭 있어야 하는값인 경우 사용하고  
**있거나 혹은 없거나 둘 중 하나인 경우**, **특정 id를 유니크하게 갖게 해야되는 상황일때 사용하며 완료된 시점**을 전달해야 하는 경우에 사용한다. 사용자에게 알림을 보내고 완료된 시점을 전달할때 사용한다.

Publisher에서는 onNext 이후 바로 onComplete를 호출하면 되기 때문에 구현이 더 간단하고, Subscriber를 구현할때도 최대 1개의 item이 반드시 전달된다는 사실을 알고 있기 때문에 코드 작성에 더 도움이 될 수 있다.  



## Reactor Subscribe
Publisher에 subscribe하지 않으면 아무 일도 일어나지 않는다.  
키보드를 컴퓨터에 연결만하고 타자를 치지 않으면 글자가 쳐지지 않는것처럼..~

아무도 subscribe 하지 않았기 때문에 아래 코드는 아무일도 하지 않는 Publisher 다
````java
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class Example {
    public static void main(String[] args) {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .doOnNext(value -> {
                    log.info("value : " + value);
                });
    }
}
````

subscribe는 3가지 종류가 있다.
1. consumer를 넘기지 않는 subscribe : 별도로 consume을 하지 ㅇ낳고 최대한으로 요청할 수 있다.  
2. 함수형 인터페이스 기반의 subscribe : Disposable을 반환하고 disposable을 통해서 언제든지 연결 종료 가능
3. Subscriber 기반의 subscribe : Subscriber는 subscription을 받기 때문에 request와 cancel을 통해서 backpressure 조절과 연결 종료가 가능하다.

````java
import io.micrometer.core.lang.Nullable;
import reactor.core.Disposable;

public final Disposable subscribe();

public final Disposable subscribe(
        @Nullable Consumer<? super T> consumer,
        @Nullable Consumer <? super Throwable> errorConsumer,
        @Nullable Runnable completeConsumer,
        @Nullable Context initialContext
        );

    public final void subscribe(Subscriber<? super T> actual);
````
Subscribe는 Publisher에서 아이템을 만드는 것이 중요한 경우 별도의 consume을 하지 않는다.
결과를 확인하기 위해서 doOnNext를 이용하며, doOnNext는 파이프라인에 영향을 주지 않고 지나가는 값을 확인하는 메서드다.

````java
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class FirstExample {
    public static void main(String[] args) {
        // 1 번 케이스
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .doOnNext(value -> {
                    log.info("value : " + value);
                }).subscribe();
    }
}
````

````java

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class SecondExample {
    public static void main(String[] args) {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        log.info("value : " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        log.error("error : " + throwable);
                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        log.info("complete");
                    }
                }, Context.empty());
    }
}
````
함수형 인터페이스를 subscribe에 제공할 수 있고, 각각의 consumer는 null이 가능하다.
1. consumer : 값을 인자로 받아야 하기 때문에 Consumer 함수형 인터페이스를 구현한다.
2. errorConsumer : 에러를 인자로 받아야 하기 때문에 Consumer를 구현한다.
3. completeConsumer : 받을 인자가 없기 때문에 Runnable을 구현한다.
4. initialContext : upstream에 전달할 context.



