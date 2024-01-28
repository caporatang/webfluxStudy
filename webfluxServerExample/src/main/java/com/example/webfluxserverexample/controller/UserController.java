package com.example.webfluxserverexample.controller;

import com.example.webfluxserverexample.controller.dto.UserResponse;
import com.example.webfluxserverexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        return userService.findById(userId) // 이렇게 반환하면 Mono 반환
                .map(user ->{
                    return new UserResponse(
                                user.getId(),
                                user.getName(),
                                user.getAge(),
                                user.getFollowCount()
                    );
                }).switchIfEmpty(
                        // 조회가 되지 않는 경우 -> 404로 변환해서 반환
                        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))
                );
    }


}