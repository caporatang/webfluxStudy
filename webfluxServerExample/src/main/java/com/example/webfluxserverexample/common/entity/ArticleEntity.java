package com.example.webfluxserverexample.common.entity;

import lombok.Data;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.blocking.common
 * fileName : ArticleEntity
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Data
public class ArticleEntity {
    // DB 개념으로 사용
    private final String id;
    private final String title;
    private final String content;
    private final String userId;
}