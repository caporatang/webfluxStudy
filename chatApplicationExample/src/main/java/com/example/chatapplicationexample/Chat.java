package com.example.chatapplicationexample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName : com.example.chatapplicationexample
 * fileName : Chat
 * author : taeil
 * date : 2/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/17/24        taeil                   최초생성
 */
@Getter @Setter @AllArgsConstructor
public class Chat {
    private String message;
    private String from;
}