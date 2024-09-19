package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.rxJava.single;

import io.reactivex.rxjava3.core.Single;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.single
 * fileName : SingleNullExample
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
public class SingleNullExample {
    // Single은 있거나, 에러를 반환
    public static void main(String[] args) {
        getItem().subscribe(new SimpleSingleObserver<>());
    }


    private static io.reactivex.rxjava3.core.Single<Integer> getItem() {
        return Single.create(SingleEmitter -> {
            // null value로 에러 발생! -> 다른 publisher에게도 null을 넘겨져도 동일한 에러가 발생되긴 함.
            SingleEmitter.onSuccess(null);
        });
    }
}