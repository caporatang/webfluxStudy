package com.example.webfluxstudy.basicReactiveProgramming.completableFuture.completableFuture.anyOf;

/**
 * packageName : com.example.webfluxstudy.completableFuture.completableFuture.anyOf
 * fileName : AnyOf
 * author : taeil
 * date : 2023/10/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/12        taeil                   최초생성
 */
public class AnyOf {
    // allOf 와 비슷하지만 반환값이 void가 아니라 Object 반환 -> 반환되는 Object는 가장 빨리 처리된 future를 반환해줌

    // 여러 completableFuture를 모아서 하나의 completableFUture로 변환할 수 있다.
    // 주어진 future 중 하나라도 완료되면 상태가 done으로 변경
    // 제일 먼저 done 상태가 되는 future의 값을 반환
}