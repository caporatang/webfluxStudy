package com.example.webfluxstudy.proactorPattern.javaAIO.completionHandler;

/**
 * packageName : com.example.webfluxstudy.proactorPattern.javaAIO.completionHandler
 * fileName : CompletionHandler
 * author : taeil
 * date : 11/13/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/13/23        taeil                   최초생성
 */
public class CompletionHandler {
    // CompletionHandler
    // completed, failed 메소드 제공
    // completed : I/O 이벤트가 완료되었을때 호출
    // failed : I/O 이벤트가 실패했을때 호출
    // accept를 담당하는 Handler 라면 V에 AsynchronousSocketChannel 전달
    // read를 담당하는 Handler 라면 V에 Integer 전달 -> 읽은 byte 수

    // interface CompletionHandler<V,A>
    // void completed(V result, A attachment);
    // void failed(Throwable exc, A attachement);
}