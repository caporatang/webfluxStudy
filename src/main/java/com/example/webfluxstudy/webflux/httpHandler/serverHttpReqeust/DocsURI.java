package com.example.webfluxstudy.webflux.httpHandler.serverHttpReqeust;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

/**
 * packageName : com.example.webfluxstudy.webflux.httpHandler.serverHttpReqeust
 * fileName : DocsURI
 * author : taeil
 * date : 1/3/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/3/24        taeil                   최초생성
 */
@Slf4j
public class DocsURI {
    // URI
    // scheme, schemeSpecificPart, authority, userInfo, host, port, path, query, fragment 등 으로 분리된다.
    // query는 key1=value2&key2=value2.. 와 같은 raw string 이기 때문에, 가능하면 ServerHttpRequest.getQueryParams 사용이 권장된다

    // ex)
    // http://abc:test@localhost:8080/api/hello?name=taeil#hame
    // abc:test@ -> userInfo
    // localhost -> host
    // :8080 -> port
    // -> userinfo, host, port 는 authority 라고한다.
    // /api/hello -> path
    // name=taeil -> query
    // #home -> fragment

    @SneakyThrows
    public static void main(String[] args) {
        URI uri = new URI("http://abc:test@localhost:8080/api/hello?name=taeil#hame");
        log.info("uri.getScheme() : {}", uri.getScheme());
        log.info("uri.getSchemeSpecificPart() : {}", uri.getSchemeSpecificPart());
        log.info("uri.getAuthoritiy() : {}", uri.getAuthority());
        log.info("uri.getUserInfo() : {}", uri.getUserInfo());
        log.info("uri.getHost() : {}", uri.getHost());
        log.info("uri.getPort() : {}", uri.getPort());
        log.info("uri.getPath() : {}", uri.getPath());
        log.info("uri.getQuery : {}", uri.getQuery());
        log.info("uri.getFragment : {}", uri.getFragment());
    }

}