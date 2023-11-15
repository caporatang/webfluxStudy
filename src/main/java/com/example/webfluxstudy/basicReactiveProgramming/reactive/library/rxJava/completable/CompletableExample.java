package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.rxJava.completable;

import io.reactivex.rxjava3.core.Completable;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.completable
 * fileName : CompletableExample
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
@Slf4j
public class CompletableExample {
    // complete나 error만 반환~

    public static void main(String[] args) {
        getCompletion().subscribe(new SimpleCompletableObserver());
    }

    private static Completable getCompletion() {
        return Completable.create(CompletableEmitter -> {
            Thread.sleep(1000);
            // complete
            CompletableEmitter.onComplete();
        });
    }

}