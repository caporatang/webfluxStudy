package com.example.webfluxstudy.reactor.context.contextWrite;

/**
 * packageName : com.example.webfluxstudy.reactor.context.contextWrite
 * fileName : DocsContextWrite
 * author : taeil
 * date : 12/31/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/31/23        taeil                   최초생성
 */
public class DocsContextWrite {
    // Context를 인자로 받고 Context를 반환하는 함수형 인터페이스를 제공한다. -> Function
    // 이를 통해서 기존의 Context에 값을 추가하거나 변경, 삭제 가능하다. -> Function을 받아서 기존의 context도 변경이 되는가? 아니다. 새로운 context 생성
    // Context는 immutable 하기 때문에 각각의 작업은 새로운 Context를 생성한다. -> 쓰레드 세이프하다.
}