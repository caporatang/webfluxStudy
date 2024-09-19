package com.example.webfluxstudy.webflux.webHandler.exception;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.exception
 * fileName : CustomExcpetion
 * author : taeil
 * date : 1/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/17/24        taeil                   최초생성
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}