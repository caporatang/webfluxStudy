package com.example.springdatareactive.example.controller;

import com.example.springdatareactive.example.common.User;
import com.example.springdatareactive.example.controller.dto.ProfileImageResponse;
import com.example.springdatareactive.example.controller.dto.SignupUserRequest;
import com.example.springdatareactive.example.controller.dto.UserResponse;
import com.example.springdatareactive.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * packageName : com.example.webfluxserverexample.controller
 * fileName : UserController
 * author : taeil
 * date : 1/28/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/28/24        taeil                   최초생성
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    // 이미지 서버는 Router 사용

    private final UserService userService;

    @GetMapping("/{userId}")
    public Mono<UserResponse> getUserById(
            @PathVariable String userId
    ) {
        return ReactiveSecurityContextHolder
                .getContext()
                .flatMap(context -> {
                    String name = context.getAuthentication().getName();
                    if (!name.equals(userId)) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
                    }
                    return userService.findById(userId) // 이렇게 반환하면 Mono 반환
                            .map(this::userMap).switchIfEmpty(
                                    // 조회가 되지 않는 경우 -> 404로 변환해서 반환
                                    Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))
                            );
                });
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public Mono<UserResponse> signupUser(
            @RequestBody SignupUserRequest request
    ) {
        return userService.createUser(
                request.getName(),
                request.getAge(),
                request.getPassword(),
                request.getProfileImageId())
                .map(this::userMap);
    }

    private UserResponse userMap(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getFollowCount(),
                user.getProfileImage().map(image ->
                        new ProfileImageResponse(
                                image.getId(),
                                image.getName(),
                                image.getUrl()
                        ))
        );
    }


}