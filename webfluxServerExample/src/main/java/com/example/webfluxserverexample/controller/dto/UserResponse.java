package com.example.webfluxserverexample.controller.dto;

import lombok.Data;

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
}