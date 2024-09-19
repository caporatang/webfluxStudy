package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.future;

import java.util.concurrent.*;
import java.util.concurrent.Future;

/**
 * packageName : com.example.webfluxstudy.completableFuture.future
 * fileName : FutureHelper
 * author : taeil
 * date : 2023/10/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/02        taeil                   최초생성
 */
public class FutureHelper {
    public static Future<Integer> getFuture() {
        // 새로운 쓰레드를 생성하여 1을 반환
        var executor = Executors.newSingleThreadExecutor();

        try {
            return executor.submit(() -> {
                return 1;
            });
        } finally {
            executor.shutdown();
        }
    }

    public static Future<Integer> getFutureCompleteAfter1s() {
        // 새로운 쓰레드를 생성하고 1초 대기 후 1을 반환
        var executor = Executors.newSingleThreadExecutor();

        try {
            return executor.submit(() -> {
                Thread.sleep(1000);
                return 1;
            });
        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // future 의 상태를 반환하는 메서드
        // isDone : task가 완료되었다면, 원인과 상관없이 true 반환 -> Exception, complete, cancel 상태와는 상관없이 무조건 true가 반환된다.
        // isCancelled : task가 명시적으로 취소된 경우, true 반환

        // Future.get()
        // 결과를 구할 때까지 thread가 계속 block
        // future에서 무한 루프나 오랜 시간이 걸린다면 thread가 blocking 상태를 유지
        Future future = FutureHelper.getFuture();
        assert !future.isDone();
        assert !future.isCancelled();

        var result = future.get();
        assert result.equals(1);
        assert future.isDone();
        assert !future.isCancelled();


        // Future : get(long timeout, TimeUnit unit) --> 개발자의 실수로 계속 무한루프가 돌거나 할때 시간을 지정할 수 있는 메서드
        // 결과를 구할 때까지 timeout동안 thread가 block
        // timeout이 넘어가도 응답이 반환되지 않으면 TimeoutException 발생 !
        Future future2 = FutureHelper.getFutureCompleteAfter1s();
        var result2 = future2.get(1500, TimeUnit.MICROSECONDS);
        assert result2.equals(1);

        Future futureToTimeout = FutureHelper.getFutureCompleteAfter1s();
        Exception exception = null;
        try {
            futureToTimeout.get(500, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e ) {
            exception = e;
        }
        assert exception != null;

        // Future : cancel(boolean mayInterruptIfRunning)
        // future의 작업 실행을 취소
        // 취소할 수 없는 상황이라면 false를 반환 -> 이미 cancel된 경우...등등
        // mayInterruptIfRunning이 false라면 시작하지 않은 작업에 대해서만 취소
         Future future3 = FutureHelper.getFuture();
         var successToCancel = future.cancel(true);
         assert future3.isCancelled();
         assert future3.isDone();
         assert successToCancel;

         successToCancel = future.cancel(true);
         assert future3.isCancelled();
         assert future3.isDone();
         assert !successToCancel;
    }

}