package com.example.webfluxserverexample.service;

import com.example.webfluxserverexample.common.User;
import com.example.webfluxserverexample.repository.UserReactorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
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
    private final UserReactorRepository userRepository = new UserReactorRepository();

    public Mono<User> findById(String userId) {
        return userRepository.findById(userId)
                .map(userEntity ->
                        new User(userEntity.getId(),
                                userEntity.getName(),
                                userEntity.getAge(),
                                Optional.empty(),
                                List.of(),
                                0L
                        )
                );
    }

}