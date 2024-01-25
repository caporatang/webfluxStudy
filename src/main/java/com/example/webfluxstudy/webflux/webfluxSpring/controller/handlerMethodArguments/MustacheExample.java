package com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments
 * fileName : DocsMustache
 * author : taeil
 * date : 1/25/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/25/24        taeil                   최초생성
 */
@Controller
public class MustacheExample {
    // html을 생성하는 template engine
    // 다양한 로직을 제공하지는 않지만 간단하게 사용 가능
    // spring-boot-starter-mustache를 추가하면 MustacheReactiveWebConfiguration이 동륵된다.
    // UrlBasedViewResolver를 상속한 MustacheViewResolver를 bean으로 등록한다.

    // spring.mustache.suffix를 .html로 설정한다.
    // spring.mustache.prefix는 classpath:/templates/
    // view name이 주어지면 MustacheViewResolver는 classpath:/templates/<view name>.html 을 찾아서 model의 값을 채우고 render 한다.

    @GetMapping("/hello-html")
    Mono<Rendering> helloHtml(@RequestParam String name) {
        // hello 라는 view name을 갖는 Rendering 반환
        // MustacheViewResolver는 classpath:/templates/hello.html을 찾아서 modelAttribute로 받은 name을 채우고 render
        // status, header를 설정
        var rendering = Rendering.view("hello")
                .modelAttribute("name", name)
                .status(HttpStatus.CREATED)
                .header("X-Custom-Name", name)
                .build();
        return Mono.just(rendering);
    }
}