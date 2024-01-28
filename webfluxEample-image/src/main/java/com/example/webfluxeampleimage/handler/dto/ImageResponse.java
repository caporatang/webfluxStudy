package com.example.webfluxeampleimage.handler.dto;

import lombok.Data;

/**
 * packageName : com.example.webfluxeampleimage.handler.dto
 * fileName : ImageResponse
 * author : taeil
 * date : 1/28/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/28/24        taeil                   최초생성
 */
@Data
public class ImageResponse {
    private final String id;
    private final String name;
    private final String url;
}