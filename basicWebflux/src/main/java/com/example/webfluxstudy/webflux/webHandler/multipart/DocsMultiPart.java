package com.example.webfluxstudy.webflux.webHandler.multipart;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.multipart
 * fileName : DocsMultiPart
 * author : taeil
 * date : 1/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/13/24        taeil                   최초생성
 */
public class DocsMultiPart {
    // MultipartData는 여러 part로 구성
    // Plain text 혹은 파일 등을 보낼 수 있고
    // boundary로 하나하나 독립적인 영역으로 구분한다

    // EX)
    //POST http://localhost:8080
    //Content-Type: multipart/form-data; boundary=WebAppBoundary
    //
    //WebAppBoundary
    //Content-Disposition: form-data; name="name"
    //Content-Type: text/plain
    //
    //taeil
    //WebAppBoundary
    //COntent-Disposition: form-data; name="data"; filename="data.json"
    //Content-Type: application/json
    //
    //./data.json


}