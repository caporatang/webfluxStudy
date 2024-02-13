package com.example.webfluxstudy.serverSentEvent.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName : com.example.webfluxstudy.serverSentEvent.controller
 * fileName : Event
 * author : taeil
 * date : 2/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/13/24        taeil                   최초생성
 */
@Getter
@Setter
public class Event {
    private String type;
    private String message;
}