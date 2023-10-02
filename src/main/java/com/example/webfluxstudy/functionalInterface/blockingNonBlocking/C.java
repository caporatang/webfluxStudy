package com.example.webfluxstudy.functionalInterface.blockingNonBlocking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * packageName : com.example.webfluxstudy.blockingNonBlocking
 * fileName : C
 * author : taeil
 * date : 2023/09/28
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/28        taeil                   최초생성
 */
@Slf4j
public class C {
    public static void main(String[] args) throws InterruptedException {
      // 작업에 대한 결과를 계속 확인함.

      // A 모델
      // getResult를 호출한 후 getResult가 완료되지 않으면 main은 본인의 일을 할 수 없다.
      // C 모델
      // getResult를 호출한 후, getResult가 완료되지 않더라도 main은 본인의 일을 할 수 있다. -> log..등

      // Non-Blocking
      // callee를 호출한 후, callee가 완료되지 않더라도 caller는 본인의 일을 할 수 있다.
      // 제어권을 caller가 가지고 있다.

      log.info("Start main");

      var count = 1;
        Future<Integer> result = getResult();
        while(!result.isDone()) {
            log.info("wating for result, count : {}", count++);
            Thread.sleep(100);
        }

//        var nextValue = result.get() +1;
//        assert nextValue = 1;

        log.info("Finish main");
    }

    public static Future<Integer> getResult() {
        var excutor = Executors.newSingleThreadExecutor();
        try {
            return excutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    log.info("Start getResult");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    var result = 0;
                    try {
                        return result;
                    } finally {
                        log.info("Finish getResult");
                    }
                }
            });
        } finally {
            excutor.shutdown();
        }
    }

}