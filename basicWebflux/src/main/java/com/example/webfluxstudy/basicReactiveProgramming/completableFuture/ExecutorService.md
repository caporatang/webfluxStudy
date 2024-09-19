## ExecutorService
- 쓰레드 풀을 이용하여 비동기적으로 작업을 실행하고 관리한다.
- 별도의 쓰레드를 생성하고 관리하지 않아도 되므로, 코드를 간결하게 유지 가능하다.
- 쓰레드 풀을 이용하여 자원을 효율적으로 관리할 수 있다.  

  
````java
import java.util.concurrent.Executor;

public interface ExecutorService extends Executor {
    void execute(Runnable command);
    <T> Future<T> submit(Callable<T> task);
    void shutdown();
}
````
- execute : Runnable 인터페이스를 구현한 작업을 쓰레드 풀에서 비동기적으로 실행한다.
- submit : Callable 인터페이스를 구현한 작업을 쓰레드 풀에서 비동기적으로 실행하고, 해당 작업의 결과를 Future<T> 객체로 반환한다.
- shutdown : ExecutorService를 종료. 더 이상 task를 받지 않는다.

### Executors - ExecutorService 생성
- newSingleThreadExecutor : 단일 쓰레드로 구성된 스레드 풀을 생성. 한 번에 하나의 작업만 실행
- newFixedTHreadPool : 고정된 크기의 쓰레드 풀을 생성. 크기는 인자로 주어진 n과 동일하다.
- newCachedThreadPool : 사용 가능한 쓰레드가 없다면 새로 생성해서 작업을 처리하고, 있다면 재사용. 쓰레드가 일정 시간 사용되지 않으면 회수한다.
- newScheduledThreadPool : 스케줄링 기능을 갖춘 고정 크기의 쓰레드 풀을 생성한다. 주기적이거나 지연이 발생하는 작업을 실행한다.
- newWorkStealingPool : work steal 알고리즘을 사용하는 ForkJoinPool을 생성한다.
````java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newScheduledThreadPool();
        ExecutorService executorService = Executors.newWorkStealingPool();
    }
}
````
