package com.example.webfluxstudy.reactor.errorHandling.onErrorMap;

/**
 * packageName : com.example.webfluxstudy.reactor.errorHandling.onErrorMap
 * fileName : CustomBusinessException
 * author : taeil
 * date : 12/25/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 12/25/23        taeil                   최초생성
 */
public class CustomBusinessException extends RuntimeException{
    public CustomBusinessException(String mmessage) {
        super(mmessage);
    }
}