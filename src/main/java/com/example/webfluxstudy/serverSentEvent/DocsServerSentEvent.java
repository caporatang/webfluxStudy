package com.example.webfluxstudy.serverSentEvent;

/**
 * packageName : com.example.webfluxstudy.serverSendEvent
 * fileName : DocsServerSentEvent
 * author : taeil
 * date : 2/9/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/9/24        taeil                   최초생성
 */
public class DocsServerSentEvent {
    // ServerSendEvent 란?
    // Chunked Transfer-Encoding 기반
    // chunk 단위로 여러 줄로 구성된 문자열을 전달
    // new line으로 이벤트를 구분한다.
    // 문자열은 일반적으로 <field>:<value> 형태로 구성한다.

    // Server Send Event filed
    // id : 이벤트의 id를 가리킨다.
    //  1. client에서는 이벤트의 id를 저장하고 Last-Event-ID 헤더에 첨부하여 가장 마지막으로 받은 이벤트가 무엇인지 전달한다.
    //  2. 이를 이용해서 서버는 lastEventId 보다 큰 이벤트만 전달 가능하다 .
    // event : 이벤트의 타입을 표현한다.
    // data : 이벤트의 data를 표현한다.
    //  1. 여러 줄의 data 필드를 이용하면 multi line data를 표현 가능하다.
    // retry : reconnection 을 위한 대기 시간을 클라이언트에게 전달한다.
    // comment : field 부분이 빈 케이스, 기능을 한다기보다는 정보를 넘기기 위한 역할이다.

    // Return value
    // 1. Servlet stack의 return value와 거의 비슷하다. 하지만 ModelAndView 대신 Rendering을 지원한다.
    // 2. void를 반환할때 필요로 하는 argument의 차이가 있다.
    // 3. Servlet stack 에서는 HttpMessageConverter를 사용하지만 reactive stack에서는 HttpMessageWriter를 사용한다.

    // Server Send Event Controller 의 처리 과정
    // 1. ServerWebExchange로 요청이 들어온다.
    // 2. RequestMapping HandlerMapping 에서 handlerMethod를 RequestMapping HandlerAdapter 에 전달
    // 3. RequestMapping HandlerAdapter 에서는 요청을 처리하고 HandlerResult를 만드는데 Flux<ServerSentEvent> 를 포함하고 있는 result임
    // 4. ResponseBody ResultHandler가 요청을 처리한다. 그리고 만들어진 Flux<ServerSentEvent>를 가지고 응답을 내보내게 된다.

}