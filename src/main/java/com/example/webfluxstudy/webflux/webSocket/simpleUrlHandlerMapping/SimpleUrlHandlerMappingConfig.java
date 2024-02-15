package com.example.webfluxstudy.webflux.webSocket.simpleUrlHandlerMapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.server.WebHandler;

import java.util.Map;

/**
 * packageName : com.example.webfluxstudy.webflux.webSocket
 * fileName : SimpleUrlHandlerMappingConfig
 * author : taeil
 * date : 2/15/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/15/24        taeil                   최초생성
 */
@Configuration
public class SimpleUrlHandlerMappingConfig {
    // urlMap을 생성하고 /sumh-greet path에 GreetHandler 추가
    // SimpleUrlHandlerMapping 객체를 만들고 order를 1로 만들고 urlMap을 추가

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
        Map<String, WebHandler> urlMap = Map.of(
                 // 해당 path로 요청이 들어오면 GreetWebHandler가 처리할거야 . 를 등록한것.
                "/sumh-greet", new GreetWebHandler()
        );
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(1); // 우선순위 설정
        mapping.setUrlMap(urlMap);
        return mapping;
    }
}