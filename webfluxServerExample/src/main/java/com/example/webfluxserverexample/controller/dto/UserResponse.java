package com.example.webfluxserverexample.controller.dto;

import com.example.webfluxserverexample.common.Image;
import com.example.webfluxserverexample.service.ImageResponse;
import lombok.Data;

import java.util.Optional;

/**
 * packageName : com.example.webfluxserverexample.controller.dto
 * fileName : UserResponse
 * author : taeil
 * date : 1/28/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/28/24        taeil                   최초생성
 */
@Data
public class UserResponse {
    private final String id;
    private final String name;
    private final int age;
    private final Long followCount;
    private final Optional<ProfileImageResponse> image;
}