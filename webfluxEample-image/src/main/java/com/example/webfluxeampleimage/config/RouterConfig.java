package com.example.webfluxeampleimage.config;

import com.example.webfluxeampleimage.handler.ImageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * packageName : com.example.webfluxeampleimage.config
 * fileName : RouterConfig
 * author : taeil
 * date : 1/28/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/28/24        taeil                   최초생성
 */
@Configuration
public class RouterConfig {

    @Bean
    RouterFunction router(
            ImageHandler imageHandler
    ) {
        return route()
                .path("/api", b1 -> b1
                        .path("/images",
                                b2 -> b2.GET("/{imageId:[0-9]+}", imageHandler::getImageById)
                        )
                ).build();
    }
}