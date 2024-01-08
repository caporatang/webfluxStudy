package com.example.webfluxstudy.webflux.httpHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import javax.swing.*;
import java.awt.image.DataBuffer;

/**
 * packageName : com.example.webfluxstudy.webflux.httpHandler
 * fileName : HttpHandlerExample
 * author : taeil
 * date : 1/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/8/24        taeil                   최초생성
 */
@Slf4j
public class HttpHandlerExample {
    public static void main(String[] args) throws InterruptedException {
        var httpHandler = new HttpHandler() {
            @Override
            public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
                String nameQuery = request.getQueryParams().getFirst("name"); // 쿼리 파람으로 접근
                String name = nameQuery == null ? "world" : nameQuery;

                String content = "Hello " + name; // name 으로 content 만듬
                log.info("responseBody : {}", content);
                var responseBody = Mono.just(
                        // body를 담은 DataBuffer를 생성해서 Mono로 wrapping -> bufferfactory를 사용하면 쉽게 buffer 객체를 만들 수 있음
                        response.bufferFactory()
                                .wrap(content.getBytes())
                );
                response.addCookie(ResponseCookie.from("name", name).build());
                response.getHeaders().add("Content-Type", "text/plain");
                return response.writeWith(responseBody); // Mono로 wrapping한 DataBuffer 객체 전달
            }
        };

        // HttpHandler 객체 실행하기
        // ReactorHttpHandlerAdapter로 감싸서 HttpServer의 handle에 전달한다. Netty와 비슷하다?
        // HttpHandler는 Netty를 감싸서 Reactor로 제공하는 형태이다.
        var adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();

        // 아래 Netty와의 비교 ->
        // Reactor Netty에서는 별도의 channel과 eventLoopGroup를 명시하지 않는다.
        // read I/O event가 완료되었을 때
        // -> Netty에서는 pipeline에 ChannelHandler를 추가한다
        // -> Reactor Netty에서는 HttpHandler를 구현하여 ReactorHttpHandlerAdapter로 감싸서 전달한다.

        //var handler = new ChannelInitializer<Channel>() {
        //    @Override
        //    protected void initChannel(Channel ch) throws Exception {
        //
        //    };
        //    (new ServerBootstrap()).group(parentGroup, childGroup)
        //    .channel(NioServerSocketChannel.class)
        //    .childHandler(handler)
        //    .bind("localhost", 8080)
        //    .channel().closeFuture().sync();
        //}


    }
};
