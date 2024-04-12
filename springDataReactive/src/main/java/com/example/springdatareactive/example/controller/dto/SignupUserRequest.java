package com.example.springdatareactive.example.controller.dto;

import lombok.Data;

/**
 * packageName : com.example.springdatareactive.example.controller.dto
 * fileName : SignupUserRequest
 * author : taeil
 * date : 4/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/13/24        taeil                   최초생성
 */
@Data
public class SignupUserRequest {
    private String name;
    private Integer age;
    private String password;
    private String profileImageId;
}