package com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.future;

import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.common.Article;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.common.Image;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.common.User;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.common.entity.UserEntity;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.future.repository.FollowFutureRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.future.repository.ImageFutureRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.future.repository.UserFutureRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.future.repository.ArticleFutureRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.blocking
 * fileName : UserBlockingRepository
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Slf4j
@RequiredArgsConstructor
public class UserFutureService {
    private final UserFutureRepository userRepository;
    private final ArticleFutureRepository articleRepository;
    private final ImageFutureRepository imageRepository;
    private final FollowFutureRepository followRepository;

    @SneakyThrows
    public CompletableFuture<Optional<User>> getUserById(String id) {
        return userRepository.findById(id)
                .thenComposeAsync(this::getUser);
        }

        @SneakyThrows
        private CompletableFuture<Optional<User>> getUser(Optional<UserEntity> userEntityOptional) {
            if (userEntityOptional.isEmpty()) {
                return CompletableFuture.completedFuture(Optional.empty());
            }

            var userEntity = userEntityOptional.get();

            var imageFuture = imageRepository.findById(userEntity.getProfileImageId())
                    .thenApplyAsync(imageEntityOptional ->
                            imageEntityOptional.map(imageEntity ->
                                    new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl())
                            )
                    );

            var articlesFuture = articleRepository.findAllByUserId(userEntity.getId())
                    .thenApplyAsync(articleEntities ->
                            articleEntities.stream()
                                    .map(articleEntity ->
                                            new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent())
                                    )
                                    .collect(Collectors.toList())
                    );

            var followCountFuture = followRepository.countByUserId(userEntity.getId());

            return CompletableFuture.allOf(imageFuture, articlesFuture, followCountFuture)
                    .thenAcceptAsync(v -> {
                        log.info("Three futures are completed");
                    })
                    .thenRunAsync(() -> {
                        log.info("Three futures are also completed");
                    })
                    .thenApplyAsync(v -> {
                        try {
                            // get은 2가지 방식으로 동작함
                            // 1. future가 isDone 상태가 아니라면 isDone 상태가 될떄까지 대기
                            // 2. isDone 상태라면 즉시 반환함
                            var image = imageFuture.get();
                            var articles = articlesFuture.get();
                            var followCount = followCountFuture.get();

                            return Optional.of(
                                    new User(
                                            userEntity.getId(),
                                            userEntity.getName(),
                                            userEntity.getAge(),
                                            image,
                                            articles,
                                            followCount
                                    )
                            );
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
