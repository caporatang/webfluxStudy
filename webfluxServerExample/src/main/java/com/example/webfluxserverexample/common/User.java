package com.example.webfluxserverexample.common;

import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.common
 * fileName : User
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Data
public class User {
    private final String id;
    private final String name;
    private final int age;
    private final Optional<Image> profileImage;
    private final List<Article> articleList;
    private final Long followCount;
}