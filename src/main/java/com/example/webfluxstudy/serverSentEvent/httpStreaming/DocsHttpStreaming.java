package com.example.webfluxstudy.serverSentEvent.httpStreaming;

/**
 * packageName : com.example.webfluxstudy.serverSentEvent.httpStreaming
 * fileName : DocsHttpStreaming
 * author : taeil
 * date : 2/9/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/9/24        taeil                   최초생성
 */
public class DocsHttpStreaming {
    // Http Streaming
    // 데이터 단위는 chunk 단위로 주고 받는다. -> 청크는 가볍기 때문에 부하가 그렇게 크지 않다 .
    // 클라이언트가 서버에게 요청한다.
    // 서버가 전달할 이벤트, 데이터 등이 있다면 응답 일부분을 전달한다.
    // 요청이나 연결을 닫지 않고 다시 이벤트, 데이터를 전달할 때까지 대기한다.

    // 동적으로 content가 생성되는 경우, 정확한 Content-length를 미리 제공할 수 없기 때문에 아래의 방식으로 HTTP Streaming이 구현된다.
    // Transfer-Encoding 헤더 :
    // 1. Transfer-Encoding : chunked를 헤더에 추가한다.
    // 2. 텅 빈 chunk 를 전달하기 전까지 값을 읽는다.
    // 3. HTTP/1.1 이상에서만 사용 가능하다.
    //  EOF (End Of File)
    // 1. Connection: close를 헤더에 추가한다.
    // 2. 서버가 연결을 종료할때까지 들어오는 값을 읽는다. -> 계속해서 대기하고 있어야한다.
}