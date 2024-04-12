package com.example.springdatareactive.example.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName : com.example.webfluxserverexample.service
 * fileName : ProfileImageResponse
 * author : taeil
 * date : 1/31/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/31/24        taeil                   최초생성
 */
@Getter
@Setter
public class ImageResponse {
    private String id;
    private String name;
    private String url;

}