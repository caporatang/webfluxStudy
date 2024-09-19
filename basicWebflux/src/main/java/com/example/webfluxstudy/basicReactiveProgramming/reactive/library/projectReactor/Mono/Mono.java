package com.example.webfluxstudy.basicReactiveProgramming.reactive.library.projectReactor.Mono;

/**
 * packageName : com.example.webfluxstudy.reactive.library.projectReactor.Mono
 * fileName : Mono
 * author : taeil
 * date : 2023/10/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/20        taeil                   최초생성
 */
public class Mono {
    // 0..1 개의 item을 전달
    // 에러가 발생하면 error signal을 전달하고 종료,
    // 모든 item을 전달했다면 complete signal 전달하고 종료 .

    // MONO는 한개의 값만 잘 전달하면 되므로, 내부적으로 하나의 아이템을 onNext 하게 되면 complete를 호출하게 되어있음
}