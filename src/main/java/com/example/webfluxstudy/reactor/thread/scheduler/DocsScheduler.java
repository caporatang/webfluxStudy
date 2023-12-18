package com.example.webfluxstudy.reactor.thread.scheduler;

/**
 * packageName : com.example.webfluxstudy.reactor.thread.scheduler
 * fileName : DocsScheduler
 * author : taeil
 * date : 12/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/18/23        taeil                   최초생성
 */
public class DocsScheduler {
    // Publish 혹은 Subscribe에 어떤 Scheduler가 적용되었는가에 따라서 task를 실행하는 쓰레드풀이 결정된다.
    // 즉시 task를 실행하거나 delay를 두고 실행 가능하다.

    // Scheduler <- 구현체 : ImmediateScheduler, SingleScheduler, ParellelScheduler, BoundedElasticScheduler
}