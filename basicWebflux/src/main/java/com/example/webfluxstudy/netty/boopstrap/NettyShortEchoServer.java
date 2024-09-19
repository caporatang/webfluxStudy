package com.example.webfluxstudy.netty.boopstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.example.webfluxstudy.netty.echoServer.NettyEchoServer.echoHandler;

/**
 * packageName : com.example.webfluxstudy.netty.boopstrap
 * fileName : NettyShortEchoServer
 * author : taeil
 * date : 11/20/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/20/23        taeil                   최초생성
 */
@Slf4j
public class NettyShortEchoServer {
    // Bootstrap 객체는 netty 서버, 클라이언트를 쉽게 만들 수 있게 편의 method 제공
    // group : EventLoopGroup 등록. 각각 parent(accept 이벤트), child(read 이벤트)
    // channel : Channel class 를 기반으로 instance 자동으로 생성
    // childHandler : connect 되었을때 실행할 코드
    // bind : 특정 호스트, 포트에 bind하고 channelFuture 반환
    // ChannelInitializer : socketChannel이 등록되면 initChannel 실행

    // Bootstrap 객체는 이벤트 루프 그룹을 만들고 accept를 추가하고, socketChannel을 등록하고... 이러한 반복적인 일을 줄이기 위해 사용하는것임.
    // 으따 어렵네 .
    @SneakyThrows
    public static void main(String[] args) {
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup(4);

        var bootstrap = new ServerBootstrap();
        var executorGroup = new DefaultEventExecutorGroup(4);
        var stringEncoder = new StringEncoder();
        var stringDecoder = new StringDecoder();

        var bind = bootstrap
                .group(parentGroup, childGroup) // 두개의 인자는 첫번쨰 루프 그룹은 accept , 두번째 루프 그룹은 read
                .channel(NioServerSocketChannel.class) // 채널을 따로 생성하지 않고 class를 넘기면 채널에 해당하는 Instance 를 생성하고 register 까지 담당해준다.
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    // accept 이벤트가 들어왔을 때 어떤 handler를 실행할것인가.
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // socketChannel이 accept가 완료되서, read를 할수있는 시점에 어떤 파이프라인을 실행할것인가 에 대한 정의.
                        // 앞에서 acceptor를 만들고 socketChannel에 접근해서 childGroup을 등록한것이 이 childHanlder 몇줄로 되는것임..!
                        ch.pipeline()
                                .addLast(executorGroup, new LoggingHandler(LogLevel.INFO))
                                .addLast(stringEncoder, stringDecoder, echoHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .bind(8080);

        bind.sync().addListener(future -> {
            if(future.isSuccess()) {
                log.info("Server bound to port 8080");
            }
        });
    }
}