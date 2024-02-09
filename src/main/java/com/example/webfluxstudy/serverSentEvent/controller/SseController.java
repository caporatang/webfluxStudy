package com.example.webfluxstudy.serverSentEvent.controller;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.time.Duration;

/**
 * packageName : com.example.webfluxstudy.serverSentEvent.controller
 * fileName : SseController
 * author : taeil
 * date : 2/9/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/9/24        taeil                   최초생성
 */
@RequestMapping("/sse")
public class SseController {


    @ResponseBody
    @GetMapping(path ="/simple", produces = "text/event-stream")
    // response의 Content-Type이 "text/event-stream임을 명시." -> text/event-stream을 처리할 수 있는 writer를 찾을때 사용
    Flux<String> simpleSse() {

        // 100ms 간격으로 하나씩 증가된 값을 만드는 Flux 생성
        return Flux.interval(Duration.ofMillis(100))
        // ServerSentEvent 객체를 반환하는게 아니기 떄문에 id가 없고 event또한 default 값인 "message"로 전달된다
                .map(i -> "Hello" + i);
    }

    // id를 이용해서 id 이후 이벤트부터 클라이언트에서 받고 싶다면 어떻게 해야할까 ?
    // ServerSendEvent 의 builder를 사용해서 id, event, retry, comment, data를 설정한다.
    @ResponseBody
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ServerSentEvent<String>> sse(
            @RequestHeader(name = "Last-Event-ID",
            required = false, defaultValue = "0") Long lastEventId
    ) {
        // String이 아닌 ServerSendEvent 객체 반환
        // Last-Event-Id 를 RequestHeader로 받고, 없다면 0으로 초기화
        // event를 add로 설정
        // id는 lastEventId + 1 에 index를 추가
        // data는 index를 suffix로 설정
        return Flux.range(0, 5)
                .delayElements(Duration.ofMillis(100))
                    .map(i -> ServerSentEvent.<String> builder()
                            .event("add")
                            .id(String.valueOf(i + lastEventId + 1))
                            .data("data-" + i)
                            .comment("comment-" + i)
                            .build());

    }

}