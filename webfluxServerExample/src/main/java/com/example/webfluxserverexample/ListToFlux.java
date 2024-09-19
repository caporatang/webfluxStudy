package com.example.webfluxserverexample;

import reactor.core.publisher.Flux;

import java.util.List;

/**
 * packageName : com.example.webfluxserverexample
 * fileName : ListToFlux
 * author : taeil
 * date : 6/30/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 6/30/24        taeil                   최초생성
 */
public class ListToFlux {
    public static void main(String[] args) {
        // List 객체 생성
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        // List를 Flux로 변환
        Flux<Integer> flux = Flux.fromIterable(list);

        // Flux를 구독하여 각 요소를 출력
        flux.subscribe(System.out::println);
    }
}