package com.example.webfluxstudy.blocking;

import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.UserBlockingService;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.ArticleRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.FollowRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.ImageRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.blocking.repository.UserRepository;
import com.example.webfluxstudy.basicReactiveProgramming.completableFutureExample.common.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
public class UserServiceBlockingTest {
    UserBlockingService userBlockingService;
    UserRepository userRepository;
    ArticleRepository articleRepository;
    ImageRepository imageRepository;
    FollowRepository followRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        articleRepository = new ArticleRepository();
        imageRepository = new ImageRepository();
        followRepository = new FollowRepository();

        userBlockingService = new UserBlockingService(
                userRepository, articleRepository, imageRepository, followRepository
        );
    }

    @Test
    void getUserEmptyIfInvalidUserIdIsGiven() {
        // 1초만에 끝남 -> getUserById 만 조회하고 끝나기 때문임. -> 비정상 케이스

        // given
        String userId = "invalid_user_id";

        // when
        Optional<User> user = userBlockingService.getUserById(userId);

        // then
        assertTrue(user.isEmpty());
    }

    @Test
    void testGetUser() {
        // 정상 케이스

        // given
        String userId = "1234";

        // when
        Optional<User> optionalUser = userBlockingService.getUserById(userId);

        // then
        assertFalse(optionalUser.isEmpty());
        var user = optionalUser.get();
        assertEquals(user.getName(), "taeil");
        assertEquals(user.getAge(), 28);

        assertFalse(user.getProfileImage().isEmpty());
        var image = user.getProfileImage().get();
        assertEquals(image.getId(), "image#1000");
        assertEquals(image.getName(), "profileImage");
        assertEquals(image.getUrl(), "https://dailyone.com/images/1000");

        assertEquals(2, user.getArticleList().size());

        assertEquals(1000, user.getFollowCount());

        // 각각 조회 -> BlockingService에 적어둔 것처럼 테스트 결과는 총 4초의 시간이 걸림
    }
}