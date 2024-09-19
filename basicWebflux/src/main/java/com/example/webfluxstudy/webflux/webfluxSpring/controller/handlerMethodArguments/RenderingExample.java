package com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.controller.handlerMethodArguments
 * fileName : RenderingExample
 * author : taeil
 * date : 1/25/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/25/24        taeil                   최초생성
 */
@Controller
public class RenderingExample {
    // interface -> view 메서드는 일반적인 빌더, redirectTo 메서드는 리다이렉트 빌더

    // html rendering을 지원하는 객체
    // servlet의 ModelAndView 와 비슷한 역할
    // html을 그리기 위해서 view, model, status, header 등의 정보를 제공한다.
    // redirect를 위해서 다양한 option을 제공한다.


    @GetMapping("redirect")
    Mono<Rendering> redirect() {
        // redirect 에서는 status 옵션이 동작하지 않는다. -> 307을 제공했음에도 예제에서는 303으로 반환한다
        // propagateQuery를 true로 제공하는 경우, request에서 받았던 quey params를 redirectUrl에 전파
        // header를 통해서 커스텀 헤더 추가
        var rendering = Rendering.redirectTo("/test") // 리다이렉트 빌더 생성
                .status(HttpStatus.TEMPORARY_REDIRECT) // 307 상태값
                .header("X-Custom-Name", "taeil") // 헤더
                .contextRelative(true) // contextPath를 추가해서 리다이렉트를 하겠냐.
                .propagateQuery(true) // queryParam을 그대로 보낼거냐 말거냐
                .build();
        return Mono.just(rendering);
    }


    // Rendering을 사용하지 않고 리다이렉트 방법 1
    // String 을 반환하면 ViewResolutionResultHandler에서 viewname 으로 view를 찾아서 html render
    // 아래 예제 2가지는 redirect:prefix를 이용했기 때문에 redirect view가 동작한다.
    // RedirectView는 View를 구현했기 떄문에 ViewResolutionResultHandler 가 redirect
    @GetMapping("/redirect2")
    String redirect2() {
        return "redirect:/test2";
    }
    // Rendering을 사용하지 않고 리다이렉트 방법 2
    @GetMapping("/redirect3")
    RedirectView redirect3() {
        return new RedirectView("/test3");
    }
    // redirect는 템플릿 엔진을 따로 설정하지 않으면 동작하지 않는다!!!!!!!!!!!!!


}