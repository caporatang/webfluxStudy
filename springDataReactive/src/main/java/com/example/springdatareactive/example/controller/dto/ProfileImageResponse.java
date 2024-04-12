package com.example.springdatareactive.example.controller.dto;

import lombok.Data;

/**
 * packageName : com.example.webfluxserverexample.controller.dto
 * fileName : ProfileImageResponse
 * author : taeil
 * date : 1/31/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/31/24        taeil                   최초생성
 */
@Data
public class ProfileImageResponse {
    private final String name;
    private final String url;
    private final String id;
}