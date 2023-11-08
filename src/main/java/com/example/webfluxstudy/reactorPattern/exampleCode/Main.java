package com.example.webfluxstudy.reactorPattern.exampleCode;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.reactorPattern.exampleCode
 * fileName : Main
 * author : taeil
 * date : 11/8/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/8/23        taeil                   최초생성
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("start main");
        Reactor reactor = new Reactor(8080);
        reactor.run();
        log.info("end main");
    }
}