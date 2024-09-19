package com.example.webfluxstudy.reactor.sequence.handle;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;

/**
 * packageName : com.example.webfluxstudy.reactor.sequence.handle
 * fileName : DocsHandle
 * author : taeil
 * date : 12/7/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/7/23        taeil                   최초생성
 */
public class DocsHandle {
    // 독립적으로 sequence를 생성할 수는 없고 존재하는 source에 연결

    // handler
    // 첫 번째 인자로 source의 item 이 제공
    // 두 번째 인자로 SynchronousSink를 제공
    // sink의 next를 이용해서 현재 주어진 item을 전달할지 말지를 결정한다.
    // 일종의 interceptor로 source의 item을 필터 하거나 변경할 수 있다.

    // public final <R> Flux<R> handle (
    //         BiConsumer<? super T, SynchronousSink<R>> handler
    // )

    // BiConsumer는 Bifunction이랑 비슷한데
    // BiConsumer는 두개의 값을 받고 따로 내보내는 값이 없다.
}