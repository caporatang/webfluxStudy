package com.example.chatapplicationexample.config;

import com.example.chatapplicationexample.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.Map;

/**
 * packageName : com.example.chatapplicationexample.config
 * fileName : MappingConfig
 * author : taeil
 * date : 2/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/17/24        taeil                   최초생성
 */
@Configuration
public class MappingConfig {
    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(
            ChatWebSocketHandler chatWebSocketHandler
    ) {
        Map<String, WebSocketHandler> urlMapper = Map.of(
                "/chat", chatWebSocketHandler
        );

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(1);
        mapping.setUrlMap(urlMapper);

        return mapping;
    }

}