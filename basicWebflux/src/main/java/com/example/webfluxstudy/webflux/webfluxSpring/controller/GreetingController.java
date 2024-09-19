package com.example.webfluxstudy.webflux.webfluxSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.controller
 * fileName : GreetingController
 * author : taeil
 * date : 1/24/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/24/24        taeil                   최초생성
 */
@Controller
@RequestMapping("/greet")
public class GreetingController {

    @ResponseBody
    @GetMapping(params = "name")
    Mono<String> greetQueryParam(@RequestParam String name) {
        // ReqeustMapping으로 path 패턴 설정
        // ReqeustParam을 통해서 name query param에 접근
        // 응답을 Mono<String> 형태로 반환
        String content = "Hello " + name;
        return Mono.just(content);
    }

    // RestController
    // @ResponseBody를 갖고 있기 때문에 모든 handler method에 적용된다.

    // controller의 일을 처리하는 플로우는? -> DispatcherHandler에서 처리되는 과정이다
    // RequestMappingHandlerMapping을 통해서 HandlerMethod를 반환
    // RequestMappingHandlerAdapter를 통해서 HandlerMethod를 실행하고
    // ResponseBodyResultHandler가 HandlerResult로 render -> ResponseBody가 적용되었기 때문에 ResponseBodyResultHandler..!

    // FunctionalEndpoints 와 다른점은?
    // RouterMapping <-> RequestMappingHandlerMapping 의 반환값으로
    // HandlerFunction <-> HandlerMethod 가 반환된다
    // HandlerFunctionAdapter <-> ReqeustMappingHandlerAdapter 에 반환된 값을 전달한다.
    // ServerResponse가 담겨져있는 HandlerResult <-> @ResponseBody를 내부에 포함하고 있는 HandlerResult가 반환된다
    // ServerResponseResultHandler <-> ResponseBodyResultHandler 에서 처리를하고,
    // 처리된 결과를 writeTo <-> writeBody 로 처리한다.

    // 여러 타입으로 render
    // ResponseEntityResultHandler : ResponseEntity, HttpHeaders
    // ResponseBodyResultHandler : @ResponseBody
    // ViewResolutionResultHandler : @ModelAttribute, String, Rendering, Model, Map, View

}