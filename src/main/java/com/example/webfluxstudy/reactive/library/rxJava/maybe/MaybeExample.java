package com.example.webfluxstudy.reactive.library.rxJava.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.maybe
 * fileName : MaybeExample
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
public class MaybeExample {
    public static void main(String[] args) {
        maybeGetItem().subscribe(new SimpleMaybeObserver<>());
    }

    private static Maybe<Integer> maybeGetItem() {
        return Maybe.create(MaybeEmitter -> {
            MaybeEmitter.onSuccess(1);
        });
    }
}