package com.example.webfluxstudy.reactive.library.rxJava.maybe;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.maybe
 * fileName : SimpleMaybeObserver
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
@Slf4j
public class SimpleMaybeObserver<T> implements MaybeObserver {
    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.disposable = d;
        log.info("subscribe");
    }

    @Override
    public void onSuccess(@NonNull Object o) {
        log.info("item : {}", o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        log.error("error: {}", e.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("complete ! ");
    }
}