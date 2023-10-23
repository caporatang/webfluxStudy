package com.example.webfluxstudy.reactive.library.rxJava.observable;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * packageName : com.example.webfluxstudy.reactive.library.rxJava.observable
 * fileName : ObserverExample
 * author : taeil
 * date : 2023/10/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/23        taeil                   최초생성
 */
@Slf4j
public class ObserverExample {
    public static void main(String[] args) {
        getItems().subscribe(new SimpleObserver());
    }

    private static Observable<Integer> getItems() {
        return Observable.fromIterable(List.of(1,2,3,4,5));
    }

}