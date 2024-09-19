package com.example.webfluxstudy.webflux.webClient;

import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * packageName : com.example.webfluxstudy.webflux.webClient
 * fileName : WebClientExample
 * author : taeil
 * date : 1/29/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/29/24        taeil                   최초생성
 */
public class WebClientExample {
    // WebClient를 생성
    // uri, header, accept를 통해서 webClient를 설정하고, retrieve를 통해서 호출한다.

    public static void main(String[] args) {
        // 실제로 사용할땐 bean으로 등록해서 사용하면되고
        // webClient는 스레드 세이프하기 때문에 동시에 접근해도 동시성 문제 없이 사용할 수 있다 .

        WebClient webClientExample = WebClient.create();
        WebClient.Builder webClientBuilder = WebClient.builder();
        WebClient webClientWithUrl = WebClient.create("http://www.naver.com");

        WebClient webClient = WebClient.create("https://localhost:8081");

        ResponseEntity<UserInfo> resp = webClient.get()
                .uri("/user")
                .header("X-Request-Id", "1234")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(UserInfo.class)
                .block(); // main에서 사용하기 때문에 들어가는 block이다. -> restTemplate 대신 servletStack에서는 이렇게 webClient를 사용하면 된다..!

        assert resp.getBody().getId().equals("1234");
        assert resp.getStatusCode().is2xxSuccessful();
        assert resp.getHeaders().getContentType().toString().equals("application/json");
        assert resp.getHeaders().get("X-Request-Id").get(0).equals("1234");
    }

    @Data
    private static class UserInfo {
        private final String id;
        private final String name;
        private final String email;
    }
}