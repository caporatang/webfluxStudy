package com.example.webfluxserverexample.service;

import com.example.webfluxserverexample.common.EmptyImage;
import com.example.webfluxserverexample.common.Image;
import com.example.webfluxserverexample.common.User;
import com.example.webfluxserverexample.repository.UserReactorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : com.example.webfluxserverexample.service
 * fileName : UserService
 * author : taeil
 * date : 1/28/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/28/24        taeil                   최초생성
 */

@Service
public class UserService {
    private WebClient webClient = WebClient.create("http://localhost:8081");
    private final UserReactorRepository userRepository = new UserReactorRepository();

    public Mono<User> findById(String userId) {
        return userRepository.findById(userId)
                .flatMap(userEntity -> {
                    String imageId = userEntity.getProfileImageId();

                    Map<String, String> uriVariableMap = Map.of("imageId", imageId);

                    // webclient 로 이미지 서버에서 이미지 가져오기 중간에 이렇게도 사용이 가능하네 ..?
                    // Mono로 반환된다
                    return webClient.get()
                            .uri("/api/images/{imageId}", uriVariableMap)
                            .retrieve()
                            .toEntity(ImageResponse.class)
                            .map(resp -> resp.getBody())
                            .map(imageResp -> new Image(
                                    imageResp.getId(),
                                    imageResp.getName(),
                                    imageResp.getUrl()
                            )).switchIfEmpty(Mono.just(new EmptyImage()))
                            .map(image -> {
                                Optional<Image> profileImage = Optional.empty();
                                if (!(image instanceof EmptyImage)) {
                                    profileImage = Optional.of(image);
                                }
                                return new User(userEntity.getId(),
                                        userEntity.getName(),
                                        userEntity.getAge(),
                                        profileImage,
                                        List.of(),
                                        0L
                                );
                            });
                });
    }
}