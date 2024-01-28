package com.example.webfluxserverexample.common;

import lombok.Data;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.common
 * fileName : Article
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Data
public class Article {
    private final String id;
    private final String title;
    private final String content;
}