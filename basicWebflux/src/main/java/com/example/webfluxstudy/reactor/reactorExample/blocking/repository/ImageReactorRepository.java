package com.example.webfluxstudy.reactor.reactorExample.blocking.repository;

import com.example.webfluxstudy.reactor.reactorExample.common.entity.ImageEntity;
import com.example.webfluxstudy.reactor.reactorExample.common.entity.UserEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.blocking.repository
 * fileName : ImageRepository
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Slf4j
public class ImageReactorRepository {
    private final Map<String, ImageEntity> imageMap;

    public ImageReactorRepository() {
        imageMap = Map.of(
                "image#1000", new ImageEntity("image#1000", "profileImage", "https://dailyone.com/images/1000")
        );
    }

    @SneakyThrows
    public Mono<ImageEntity> findById(String id) {
        return Mono.create(sink -> {
            log.info("ImageRepository.findById: {}", id);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            var image = imageMap.get(id);
            if (image == null) {
                sink.error(new RuntimeException("image not found"));
            } else {
                sink.success(image);
            }
        });
    }

    public Mono<ImageEntity> findWithContext() {
        return Mono.deferContextual(context -> {
            Optional<UserEntity> userOptional = context.getOrEmpty("user");
            if (userOptional.isEmpty()) throw new RuntimeException("user not found");

            return Mono.just(userOptional.get().getProfileImageId());
        }).flatMap(this::findById);
    }
}