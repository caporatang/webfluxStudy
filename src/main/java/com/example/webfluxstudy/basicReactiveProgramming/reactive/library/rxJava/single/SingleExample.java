package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.rxJava.single;

import io.reactivex.rxjava3.core.Single;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.single
 * fileName : SingleExample
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
public class SingleExample {
    public static void main(String[] args) {
        getItem().subscribe(new SimpleSingleObserver<>());

    }

    private static Single<Integer> getItem() {
        // complete가 따로 없기 떄문에 1을 반환하고 동작이 끝남.
        return Single.create(SingleEmitter -> {
            SingleEmitter.onSuccess(1);
        });
    }


}