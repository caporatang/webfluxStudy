package com.example.webfluxstudy.reactor;


import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.FollowRepository;
import com.example.webfluxstudy.reactor.reactorExample.blocking.UserReactorService;
import com.example.webfluxstudy.reactor.reactorExample.blocking.repository.ArticleReactorRepository;
import com.example.webfluxstudy.reactor.reactorExample.blocking.repository.FollowReactorRepository;
import com.example.webfluxstudy.reactor.reactorExample.blocking.repository.ImageReactorRepository;
import com.example.webfluxstudy.reactor.reactorExample.blocking.repository.UserReactorRepository;
import com.example.webfluxstudy.reactor.reactorExample.common.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : com.example.webfluxstudy.blocking
 * fileName : UserServiceBlockingTest
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
public class UserServiceReactorTest {
    UserReactorService userService;
    UserReactorRepository userRepository;
    ArticleReactorRepository articleRepository;
    ImageReactorRepository imageRepository;
    FollowReactorRepository followRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserReactorRepository();
        articleRepository = new ArticleReactorRepository();
        imageRepository = new ImageReactorRepository();
        followRepository = new FollowReactorRepository();

        userService = new UserReactorService(
                userRepository, articleRepository, imageRepository, followRepository
        );
    }

    @Test
    void getUserEmptyIfInvalidUserIdIsGiven() throws ExecutionException, InterruptedException {
        // given
        String userId = "invalid_user_id";

        // when
        Optional<User> user = userService.getUserById(userId).blockOptional();

        // then
        assertTrue(user.isEmpty());
    }

    @Test
    void testGetUser() throws ExecutionException, InterruptedException {
        // given
        String userId = "1234";

        // when
        Optional<User> optionalUser = userService.getUserById(userId).blockOptional();

        // then
        assertFalse(optionalUser.isEmpty());
        var user = optionalUser.get();
        assertEquals(user.getName(), "taeil");
        assertEquals(user.getAge(), 29);

        assertFalse(user.getProfileImage().isEmpty());
        var image = user.getProfileImage().get();
        assertEquals(image.getId(), "image#1000");
        assertEquals(image.getName(), "profileImage");
        assertEquals(image.getUrl(), "https://dailyone.com/images/1000");

        assertEquals(2, user.getArticleList().size());

        assertEquals(1000, user.getFollowCount());
    }
}