package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.rxJava.maybe;

import io.reactivex.rxjava3.core.Maybe;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.maybe
 * fileName : MaybeEmptyValueExample
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
public class MaybeEmptyValueExample {
    public static void main(String[] args) {
        // maybe는 있을수도, 없을수도, 에러도 반환
        maybeGetItem().subscribe(new SimpleMaybeObserver<>());
    }

    private static Maybe<Integer> maybeGetItem(){
        return Maybe.create(MaybeEmitter -> {
            // 빈값으로 내리기
            MaybeEmitter.onComplete();
        });
    }

}