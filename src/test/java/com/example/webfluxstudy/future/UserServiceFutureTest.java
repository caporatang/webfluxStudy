package com.example.webfluxstudy.future;

import com.example.webfluxstudy.completableFutureExample.blocking.UserBlockingService;
import com.example.webfluxstudy.completableFutureExample.blocking.repository.ArticleRepository;
import com.example.webfluxstudy.completableFutureExample.blocking.repository.FollowRepository;
import com.example.webfluxstudy.completableFutureExample.blocking.repository.ImageRepository;
import com.example.webfluxstudy.completableFutureExample.blocking.repository.UserRepository;
import com.example.webfluxstudy.completableFutureExample.common.User;
import com.example.webfluxstudy.completableFutureExample.future.UserFutureService;
import com.example.webfluxstudy.completableFutureExample.future.repository.ArticleFutureRepository;
import com.example.webfluxstudy.completableFutureExample.future.repository.FollowFutureRepository;
import com.example.webfluxstudy.completableFutureExample.future.repository.ImageFutureRepository;
import com.example.webfluxstudy.completableFutureExample.future.repository.UserFutureRepository;
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
public class UserServiceFutureTest {
    UserFutureService userFutureService;
    UserFutureRepository userRepository;
    ArticleFutureRepository articleRepository;
    ImageFutureRepository imageRepository;
    FollowFutureRepository followRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserFutureRepository();
        articleRepository = new ArticleFutureRepository();
        imageRepository = new ImageFutureRepository();
        followRepository = new FollowFutureRepository();

        userFutureService = new UserFutureService(
                userRepository, articleRepository, imageRepository, followRepository
        );
    }

    @Test
    void getUserEmptyIfInvalidUserIdIsGiven() throws ExecutionException, InterruptedException {
        // 1초만에 끝남 -> getUserById 만 조회하고 끝나기 때문임. -> 비정상 케이스

        // given
        String userId = "invalid_user_id";

        // when
        Optional<User> user = userFutureService.getUserById(userId).get();

        // then
        assertTrue(user.isEmpty());
    }

    @Test
    void testGetUser() throws ExecutionException, InterruptedException {
        // 정상 케이스

        // given
        String userId = "1234";

        // when
        Optional<User> optionalUser = userFutureService.getUserById(userId).get();

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