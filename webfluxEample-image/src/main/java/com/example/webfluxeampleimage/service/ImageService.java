package com.example.webfluxeampleimage.service;

import com.example.webfluxeampleimage.entity.common.Image;
import com.example.webfluxeampleimage.repository.ImageReactorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxeampleimage.service
 * fileName : ImageService
 * author : taeil
 * date : 1/28/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/28/24        taeil                   최초생성
 */
@Service
public class ImageService {

    private ImageReactorRepository imageRepository = new ImageReactorRepository();


    public Mono<Image> getImageById(String imageId) {
        return imageRepository.findById(imageId)
                .map(imageEntity ->
                        new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl()
                        )
                );
    }

}