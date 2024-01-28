package com.example.webfluxeampleimage.entity.common;

import lombok.Data;

/**
 * packageName : com.example.webfluxstudy.completableFutureExample.common
 * fileName : Image
 * author : taeil
 * date : 2023/10/14
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/14        taeil                   최초생성
 */
@Data
public class Image {
    private final String id;
    private final String name;
    private final String url;
}