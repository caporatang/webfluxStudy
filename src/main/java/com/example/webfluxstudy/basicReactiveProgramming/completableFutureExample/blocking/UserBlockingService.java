package com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking;

import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.ArticleRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.FollowRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.ImageRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.UserRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.common.Image;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.common.Article;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.common.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
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
@RequiredArgsConstructor
public class UserBlockingService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    private final FollowRepository followRepository;

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id)
                .map(user -> {
                    var image = imageRepository.findById(user.getProfileImageId()) // findById -> 1초 대기 !
                            .map(imageEntity -> {
                                return new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl());
                            });

                    var articles = articleRepository.findAllByUserId(user.getId()) // 1초 대기 !
                            .stream().map(articleEntity ->
                                    new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent()))
                            .collect(Collectors.toList());

                    var followCount = followRepository.countByUserId(user.getId()); // 1초 대기!

                    return new User(
                            user.getId(),
                            user.getName(),
                            user.getAge(),
                            image,
                            articles,
                            followCount
                    );
                });
    }
}