package com.example.webfluxeampleimage.handler;

import com.example.webfluxeampleimage.handler.dto.ImageResponse;
import com.example.webfluxeampleimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxeampleimage.handler
 * fileName : ImageHandler
 * author : taeil
 * date : 1/28/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/28/24        taeil                   최초생성
 */
@Component
@RequiredArgsConstructor
public class ImageHandler {
    private final ImageService imageService;

    public Mono<ServerResponse> getImageById(ServerRequest serverRequest) {
        String imageId = serverRequest.pathVariable("imageId");

        return imageService.getImageById(imageId)
                .map(image ->
                        // 이미지 리스폰스를 그대로 반환은 불가능하다 -> serverResponse가 필요하기 때문이다.
                        new ImageResponse(image.getId(), image.getName(), image.getUrl())
                ).flatMap(imageResp ->
                        // 일반적인 Map을 사용하면 Mono의 Mono가 반환된다 -> flatMap을 사용해야 한다.
                        ServerResponse.ok().bodyValue(imageResp)
                ).onErrorMap(e -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 1. imageService 에서 이미지를 찾고
        // 2. 이미지가 있다면 ImageResponse 객체를 생성하고
        // 3. 값을 ServerResponse의 ok 에 bodyValue에 담아서 반환하겠다.
        // 4. 찾지 못했다면 MonoException에 404로 반환하겠다.
    }
}