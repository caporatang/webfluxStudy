package com.example.webfluxstudy.reactor.reactorExample.blocking;

import com.example.webfluxstudy.reactor.reactorExample.blocking.repository.ArticleReactorRepository;
import com.example.webfluxstudy.reactor.reactorExample.blocking.repository.FollowReactorRepository;
import com.example.webfluxstudy.reactor.reactorExample.blocking.repository.ImageReactorRepository;
import com.example.webfluxstudy.reactor.reactorExample.blocking.repository.UserReactorRepository;
import com.example.webfluxstudy.reactor.reactorExample.common.Article;
import com.example.webfluxstudy.reactor.reactorExample.common.EmptyImage;
import com.example.webfluxstudy.reactor.reactorExample.common.Image;
import com.example.webfluxstudy.reactor.reactorExample.common.User;
import com.example.webfluxstudy.reactor.reactorExample.common.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.List;
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
public class UserReactorService {
    private final UserReactorRepository userRepository;
    private final ArticleReactorRepository articleRepository;
    private final ImageReactorRepository imageRepository;
    private final FollowReactorRepository followRepository;

    @SneakyThrows
    public Mono<User> getUserById(String id) {
        return userRepository.findById(id)
                .flatMap(this::getUser);
    }

    @SneakyThrows
    private Mono<User> getUser(UserEntity userEntity) {
        Context context = Context.of("user", userEntity);

        var imageMono = imageRepository.findWithContext()
                .map(imageEntity ->
                        new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl())
                ).onErrorReturn(new EmptyImage())
                .contextWrite(context);

        var articlesMono = articleRepository.findAllWithContext()
                .skip(5)
                .take(2)
                .map(articleEntity ->
                        new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent())
                ).collectList()
                .contextWrite(context);

        var followCountMono = followRepository.countWithContext()
                .contextWrite(context);

        return Mono.zip(imageMono, articlesMono, followCountMono)
                .map(resultTuple -> {
                    Image image = resultTuple.getT1();
                    List<Article> articles = resultTuple.getT2();
                    Long followCount = resultTuple.getT3();

                    Optional<Image> imageOptional = Optional.empty();
                    if (!(image instanceof EmptyImage)) {
                        imageOptional = Optional.of(image);
                    }

                    return new User(
                            userEntity.getId(),
                            userEntity.getName(),
                            userEntity.getAge(),
                            imageOptional,
                            articles,
                            followCount
                    );
                });
    }
}