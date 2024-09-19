package com.example.webfluxstudy.reactor.context.contextRead;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

/**
 * packageName : com.example.webfluxstudy.reactor.context.contextRead
 * fileName : ContextReadFromSinkExample
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
@Slf4j
public class ContextReadFromSinkExample {
    // source에 sink가 있다면 sink.contextView로 접근 가능하다.
    // create나 generate에서 제공하는 sink를 사용하는게 가장 쉽게 접근할 수 있는 방법이다.

    public static void main(String[] args) {
        var initialContext = Context.of("name", "taeil");

        Flux.create(sink -> {
            var name = sink.contextView().get("name");
            log.info("name in create : " + name);
            sink.next(1);
        }).contextWrite(context ->
            context.put("name", "happy new year?")
        ).subscribe(null, null,null, initialContext);
    }

}