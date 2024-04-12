package com.example.springdatareactive.example.service;

import com.example.springdatareactive.example.common.EmptyImage;
import com.example.springdatareactive.example.common.Image;
import com.example.springdatareactive.example.common.User;
import com.example.springdatareactive.example.common.entity.AuthEntity;
import com.example.springdatareactive.example.common.entity.UserEntity;
import com.example.springdatareactive.example.repository.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@RequiredArgsConstructor
@Service
public class UserService {
    private WebClient webClient = WebClient.create("http://localhost:8081");
    private final UserR2dbcRepository userRepository;
    private final R2dbcEntityTemplate entityTemplate;

    public Mono<User> findById(String userId) {
        return userRepository.findById(new Long(userId))
                .flatMap(userEntity -> {
                    String imageId = userEntity.getProfileImageId();

                    Map<String, String> uriVariableMap = Map.of("imageId", imageId);

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
                                return userMap(userEntity, profileImage);
                            });
                        });
    }



    public Mono<User> createUser(
            String name, Integer age,
            String password, String profileImageId) {

        var newUser = new UserEntity(
                name,
                age,
                profileImageId,
                password
        );

        return userRepository.save(newUser)
                .flatMap(userEntity -> {
                    String token = generateRandomToken();
                    AuthEntity auth = new AuthEntity(userEntity.getId(), token);

                    return entityTemplate.insert(auth)
                            .map(authEntity -> userEntity);
                })
                .map(userEntity ->
                        userMap(userEntity, Optional.of(new EmptyImage()))
                );
    }

    private User userMap(UserEntity userEntity, Optional<Image> profileImage) {
        return new User(userEntity.getId().toString(),
                userEntity.getName(),
                userEntity.getAge(),
                profileImage,
                List.of(),
                0L
        );
    }

    private String generateRandomToken() {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            var item = (char)('A' + (Math.random() * 26));
            token.append(item);
        }
        return token.toString();
    }
}