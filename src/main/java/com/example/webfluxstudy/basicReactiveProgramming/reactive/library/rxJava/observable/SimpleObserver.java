package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.rxJava.observable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.observable
 * fileName : SimpleObserver
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
@Slf4j
public class SimpleObserver implements Observer {
    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        log.info("subscribe");
        this.disposable = d;
    }

    @Override
    public void onNext(@NonNull Object o) {
        log.info("item: {}", o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        log.error("error : {}", e.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("complete");
    }
}